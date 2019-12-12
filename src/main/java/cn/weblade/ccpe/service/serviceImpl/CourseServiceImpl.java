package cn.weblade.ccpe.service.serviceImpl;

import cn.weblade.ccpe.dao.*;
import cn.weblade.ccpe.entity.*;
import cn.weblade.ccpe.service.CourseService;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private MultipleChoiceMapper multipleChoiceMapper;
    @Autowired
    private JudgeMapper judgeMapper;
    @Autowired
    private FillBlankMapper fillBlankMapper;
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private CourseSerMapper courseSerMapper;
    @Autowired
    private  SubjectRecordMapper subjectRecordMapper;

    Map<Integer,Object>  map=new HashMap();

    /*
    * 添加试卷
    * */
    @Override
    @Transactional
    public String saveSubject(String fileName) throws Exception {
//        String fileName=file.getOriginalFilename();
        String filePath="C:\\Users\\Administrator\\Desktop\\健康教育\\";
        String cachePath="C:\\Users\\Administrator\\Desktop\\健康教育\\cache\\";
        File subjectFile=new File(filePath+fileName);
        File cacheFile=new File(cachePath+fileName);

        String subjectContext=null;
        int chooseType=0;
        int choiceCount=0;
        String[]choiceAnswer=new String[4];
        MultipleChoice multipleChoice=new MultipleChoice();
        Judge judge=new Judge();
        FillBlank fillBlank=new FillBlank();

        /*判断文件是否存在*/
        if (!subjectFile.exists()){
            cacheFile.renameTo(subjectFile);
        }else {
            return "文件已存在，请先删除文件再上传";
        }

        /*插入试卷信息*/
        Paper paper=new Paper();
        fileName=fileName.replace(".docx","");
        paper.setPaperName(fileName);
        paperMapper.insertReKey(paper);
        multipleChoice.setPaperId(paper.getPaperId());
        fillBlank.setPaperId(paper.getPaperId());
        judge.setPaperId(paper.getPaperId());


        try {
            FileInputStream fis = new FileInputStream(subjectFile);
            XWPFDocument xdoc = new XWPFDocument(fis);
            List<XWPFParagraph> paras = xdoc.getParagraphs();
            for (int i=0;i<paras.size();i++)//遍历段落
            {
                XWPFParagraph p=paras.get(i);
                //获取段落的级别
                String level = p.getStyleID();
                if (level != null) {
                        if (level.equals("1")&&p.getParagraphText().equals("选择题")) {//样式1为题型
                        chooseType=1;
                        }
                    else if (level.equals("1")&&p.getParagraphText().equals("填空题")) {//样式1为题型
                        chooseType=2;
                    }
                    else if (level.equals("1")&&p.getParagraphText().equals("判断题")) {//样式1为题型
                        chooseType=3;
                    }
                    else {
                     /*
                     * 判断选择题后的操作
                     * */
                        if (chooseType==1){
                            //样式2为内容
                            if (level.equals("2")) {
                                subjectContext = p.getParagraphText();
                                multipleChoice.setContext(subjectContext);
                            }
                            //样式3为选项
                            else if (level.equals("3")) {
                                String choice = p.getParagraphText();
                                choiceAnswer[choiceCount]=choice;
                                choiceAnswer[choiceCount].trim();
                                choiceCount++;
                                //把存在数组的选项赋给实体，最多四个选项
                                if(!paras.get(i+1).getStyleID().equals("3")){
                                    multipleChoice.setAnswer1(choiceAnswer[0]);
                                    multipleChoice.setAnswer2(choiceAnswer[1]);
                                    multipleChoice.setAnswer3(choiceAnswer[2]);
                                    multipleChoice.setAnswer4(choiceAnswer[3]);
                                    choiceCount=0;

                                }else {}
                            }
                            //样式4为答案
                            else if (level.equals("4")){
                                String answerTrue = p.getParagraphText().trim();
                                multipleChoice.setAnswerTrue(answerTrue);
                               multipleChoiceMapper.insert(multipleChoice);
                            }else{}
                        }
                        /*
                         * 判断为填空题后的操作
                         * */
                        else if(chooseType==2){
                            //样式2为内容
                            if (level.equals("2")) {
                                subjectContext = p.getParagraphText();
                                fillBlank.setContext(subjectContext);
                                fillBlank.toString();
                            }
                            else if (level.equals("4")){
                                String answer = p.getParagraphText().trim();
                                fillBlank.setAnswer(answer);
                              fillBlankMapper.insert(fillBlank);


                            }else {}
                        }
                        /*
                         * 判断为判断题后的操作
                         * */
                        else if (chooseType==3){
                            //样式2为内容
                            if (level.equals("2")) {
                                subjectContext = p.getParagraphText();
                                judge.setContext(subjectContext);
                                judge.toString();
                            }else if (level.equals("4")){
                                String answer = p.getParagraphText().trim();
                                judge.setAnswer(answer);
                                judgeMapper.insert(judge);

                            }else {}
                        }
                        //出现无格式文本
                        else {
                            System.out.println("无效文档"+p.getNumID());
                        }
                    }
                }
                else {
                    System.out.println("无效文档"+p.getNumID());
                }
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "文件不符合规范";
        }

        return "上传成功";
    }

    /*删除*/
    @Override
    @Transactional
    public String deleteSubject(String paperName) {
        try {
            String filePath = "C:\\Users\\Administrator\\Desktop\\健康教育\\";
            File file = new File(filePath + paperName+".docx");
            file.delete();
            Integer paperId = courseSerMapper.selectPaperByPaperName(paperName);

            if (paperId != null || paperId != 0) {
                fillBlankMapper.FillBlankDelete(paperId);
                judgeMapper.JudgeDelete(paperId);
                multipleChoiceMapper.MultipleChoiceDelete(paperId);
                paperMapper.PaperDelete(paperId);
                return "删除成功";
            } else {
                return "删除失败";
            }

        } catch (Exception e) {
            return "删除失败";
        }
    }
//    public int deleteSubject(String paperName) {
//       String filePath="C:\\Users\\pc\\Desktop\\flyChicken\\";
//       File file=new File(filePath+paperName+".docx");
//       file.delete();
//       int paperId=courseSerMapper.selectPaperByPaperName(paperName+".docx");
//
//       fillBlankMapper.FillBlankDelete(paperId);
//       judgeMapper.JudgeDelete(paperId);
//       multipleChoiceMapper.MultipleChoiceDelete(paperId);
//       paperMapper.PaperDelete(paperId);
//       return 0;
//}

    @Override
    public Course subjectBrowse(String fileName) throws Exception{
//        String fileName=file.getOriginalFilename();
        String filePath="C:\\Users\\Administrator\\Desktop\\健康教育\\cache\\";
        File subjectFile=new File(filePath+fileName);

        String subjectContext=null;
        int chooseType=0;
        int choiceCount=0;
        String[]choiceAnswer=new String[4];
        Course course=new Course();

        /*实例化三个对象*/
        MultipleChoice multipleChoice=new MultipleChoice();
        Judge judge=new Judge();
        FillBlank fillBlank=new FillBlank();


        /*实例化三个list*/
        List<MultipleChoice>multipleChoiceList=new ArrayList<>();
        List<Judge>judgeList=new ArrayList<>();
        List<FillBlank>fillBlankList=new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(subjectFile);
            XWPFDocument xdoc = new XWPFDocument(fis);
            List<XWPFParagraph> paras = xdoc.getParagraphs();
            for (int i=0;i<paras.size();i++)//遍历段落
            {
                XWPFParagraph p=paras.get(i);
                //获取段落的级别
                String level = p.getStyleID();
                if (level != null) {
                    if (level.equals("1")&&p.getParagraphText().equals("选择题")) {//样式1为题型
                        chooseType=1;
                    }
                    else if (level.equals("1")&&p.getParagraphText().equals("填空题")) {//样式1为题型
                        chooseType=2;
                    }
                    else if (level.equals("1")&&p.getParagraphText().equals("判断题")) {//样式1为题型
                        chooseType=3;
                    }
                    else {
                        /*
                         * 判断选择题后的操作
                         * */
                        if (chooseType==1){
                            //样式2为内容
                            if (level.equals("2")) {
                                subjectContext = p.getParagraphText();
                                multipleChoice.setContext(subjectContext);

                            }
                            //样式3为选项
                            else if (level.equals("3")) {
                                String choice = p.getParagraphText();
                                choiceAnswer[choiceCount]=choice;
                                choiceAnswer[choiceCount].trim();
                                choiceCount++;
                                //把存在数组的选项赋给实体，最多四个选项
                                if(!paras.get(i+1).getStyleID().equals("3")){
                                    multipleChoice.setAnswer1(choiceAnswer[0]);
                                    multipleChoice.setAnswer2(choiceAnswer[1]);
                                    multipleChoice.setAnswer3(choiceAnswer[2]);
                                    multipleChoice.setAnswer4(choiceAnswer[3]);
                                    choiceCount=0;

                                }else {}
                            }
                            //样式4为答案
                            else if (level.equals("4")){
                                String answerTrue = p.getParagraphText().trim();
                                multipleChoice.setAnswerTrue(answerTrue);
                                multipleChoiceList.add(multipleChoice);
                                /*再次实例化一个对象*/
                                multipleChoice=new MultipleChoice();
                            }else{}
                        }
                        /*
                         * 判断为填空题后的操作
                         * */
                        else if(chooseType==2){
                            //样式2为内容
                            if (level.equals("2")) {
                                subjectContext = p.getParagraphText();
                                fillBlank.setContext(subjectContext);
                            }
                            else if (level.equals("4")){
                                String answer = p.getParagraphText().trim();
                                fillBlank.setAnswer(answer);
                                fillBlankList.add(fillBlank);
                                fillBlank=new FillBlank();
                            }else {}
                        }
                        /*
                         * 判断为判断题后的操作
                         * */
                        else if (chooseType==3){
                            //样式2为内容
                            if (level.equals("2")) {
                                subjectContext = p.getParagraphText();
                                judge.setContext(subjectContext);
                                judge.toString();
                            }else if (level.equals("4")){
                                String answer = p.getParagraphText().trim();
                                judge.setAnswer(answer);
                                judgeList.add(judge);
                                judge=new Judge();
                            }else {}
                        }
                        //出现无格式文本
                        else {
                            System.out.println("无效文档"+p.getNumID());
                        }
                    }
                }
                else {
                    System.out.println("无效文档"+p.getNumID());
                }
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        course.setMultipleChoiceList(multipleChoiceList);
        course.setFillBlankList(fillBlankList);
        course.setJudgeList(judgeList);

        return course;
    }

    @Override
    @Transactional
    public String subjectBrowseUpload(MultipartFile file) throws Exception {
        String fileName=file.getOriginalFilename();
        String filePath="C:\\Users\\Administrator\\Desktop\\健康教育\\cache\\";
        File subjectFile=new File(filePath+fileName);

        /*保存*/
        file.transferTo(subjectFile);
        return "success";
    }

}


