package cn.weblade.ccpe.service.serviceImpl;

import cn.weblade.ccpe.dao.*;
import cn.weblade.ccpe.entity.*;
import cn.weblade.ccpe.service.CourseSerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseSerServiceImpl implements CourseSerService {
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
    @Autowired
    private CourseMapper courseMapper;

    Map<Integer,Object>  map=new HashMap();

    @Override
    public int updateSubject() {
        return 0;
    }

    /*查询*/
    @Override
    public Course querySubject(Integer paperId){
        Course course=new Course();
        System.out.println(paperId);
        course.setMultipleChoiceList(multipleChoiceMapper.selectMulByPaperId(paperId));
        course.setFillBlankList(fillBlankMapper.selectFilByPaperId(paperId));
        course.setJudgeList(judgeMapper.selectJudByPaperId(paperId));

        return course;}

    /*查试卷id的试卷名*/
    @Override
    public int queryPaperNameById(String paperName) {
        return courseSerMapper.selectByPaperName(paperName);
    }
    /*查找所有的试卷名*/
    @Override
    public List<Paper>selectPaperName() {
        List<Paper>list=courseSerMapper.selectPaperName();
//        for (int i=0;i<list.size();i++){
//            list.get(i).setPaperName(list.get(i).getPaperName().replace(".docx",""));
//        }
        return list;
    }

    /*查找所有的填空题*/
    @Override
    public List<FillBlank> queryAllFillBlank() {
        return fillBlankMapper.queryAllFillBlank();
    }

    /*查找所有的判断题*/
    @Override
    public List<Judge> queryAllJudge() {
        return judgeMapper.queryAllJudge();
    }
    /*查找所有的选择题*/
    @Override
    public List<MultipleChoice> queryAllMultipleChoice() {
        return multipleChoiceMapper.queryAllMultipleChoice();
    }

    /*判断是否已存在*/
    public int isExist(String paperName){
        return courseSerMapper.selectByPaperName(paperName);
    }

    /*打分*/
    @Transactional
    @Override
    public Map<String,Object> correctPaper(Integer paperId,Integer userId,String[] fillBlank, String[]judge, String[]multipleChoice){
        Map<String,Object>map=new HashMap();
        List<Boolean[]> fillBlankAnswerList=new ArrayList<>();

        /*获取答案*/
        List<MultipleChoice>listMul=multipleChoiceMapper.queryMultipleChoiceAnswerTrue(paperId);
        List<Judge>listJud=judgeMapper.queryJudgeAnswerTrue(paperId);
        List<FillBlank>listFil=fillBlankMapper.queryFillBlankAnswerTrue(paperId);

        /*初始化对比结果，true为答对，false为答错*/
        Boolean[]bMul=new Boolean[listMul.size()];
        Boolean[]bJud=new Boolean[listJud.size()];


        /*填空题打分并更新错题集*/
        for (int i=0;i<listFil.size();i++){

            /*处理前端和数据库填空题答案*/
            String[]strTrueAnswer=listFil.get(i).getAnswer().split(" ");
            String[]strAnSwer=fillBlank[i].split(" ",strTrueAnswer.length);
            /*初始化对比结果，true为答对，false为答错*/
            Boolean[]bFil=new Boolean[strTrueAnswer.length];
            /*比较答案*/
            for (int j=0;j<strTrueAnswer.length;j++){
                bFil[j]=strTrueAnswer[j].equals(strAnSwer[j].trim());

                if (bFil[j]==false){
                    if (subjectRecordMapper.isFilExist(userId,listFil.get(i).getFillBlankId())==null){
                        subjectRecordMapper.recordSubject(userId,listFil.get(i).getFillBlankId(),null,null,fillBlank[i]);
                    }else {}
                }else{}
            }
            fillBlankAnswerList.add(bFil);
        }


        /*判断题打分并更新错题集*/
        if (judge!=null){
        for (int i=0;i<listJud.size();i++){
            bJud[i]=listJud.get(i).getAnswer().equals(judge[i]);
            if (bJud[i]==false){
                if (subjectRecordMapper.isJudExist(userId,listJud.get(i).getJudgeId())==null){
                    subjectRecordMapper.recordSubject(userId,null,listJud.get(i).getJudgeId(),null,judge[i]);
                }
            }
        }}else {
            for (int i=0;i<listJud.size();i++){
                /*用户答案为null，全部判为false*/
                bJud[i]=false;
                if (subjectRecordMapper.isJudExist(userId,listJud.get(i).getJudgeId())==null){
                    subjectRecordMapper.recordSubject(userId,null,listJud.get(i).getJudgeId(),null,"");
                }
            }
        }

        /*选择题打分并更新错题集*/
        if (multipleChoice!=null){
        for (int i=0;i<listMul.size();i++){
            bMul[i]=listMul.get(i).getAnswerTrue().equals(multipleChoice[i]);
            if (bMul[i]==false){
                if (subjectRecordMapper.isMulExist(userId,listMul.get(i).getMultipleChoiceId())==null){
                    subjectRecordMapper.recordSubject(userId,null,null,listMul.get(i).getMultipleChoiceId(),multipleChoice[i]);
                }
            }
        }}else {
            for (int i=0;i<listMul.size();i++){
                /*用户答案为null，全部判为false*/
                bMul[i]=false;
                if (subjectRecordMapper.isMulExist(userId,listMul.get(i).getMultipleChoiceId())==null){
                    subjectRecordMapper.recordSubject(userId,null,null,listMul.get(i).getMultipleChoiceId(),"");
                }
            }
        }

        map.put("fillBlank",fillBlankAnswerList);
        map.put("judge",bJud);
        map.put("multipleChoice",bMul);

        return map;
    }

    @Override
    public Course queryAllRecordSubject(Integer userId) {
        Course course=new Course();

        /*查找全部错题的id*/
        List<SubjectRecord>list=subjectRecordMapper.queryAllRecordSubjectId(userId);

        List<Integer>fillBlankId=new ArrayList<>();
        List<Integer>multipleChoiceId=new ArrayList<>();
        List<Integer>judgeId=new ArrayList<>();

        /*分类错题id*/
        for (SubjectRecord l:list){
            if (l.getFillBlankId()!=null){
                fillBlankId.add(l.getFillBlankId());
            }else if (l.getJudgeId( )!=null){
                judgeId.add(l.getJudgeId());
            }else if (l.getMultipleChoiceId()!=null){
                multipleChoiceId.add(l.getMultipleChoiceId());
            }
        }

        /*根据错题id找到题目*/
        course.setFillBlankList(fillBlankMapper.queryFillBlankByListId(fillBlankId));
        course.setJudgeList(judgeMapper.queryJudgeByListId(judgeId));
        course.setMultipleChoiceList(multipleChoiceMapper.queryMultipleChoiceByListId(multipleChoiceId));

        map.put(userId,course);
        return course;
    }

    @Override
    @Transactional
    public Map<String,Object> correctWrongSubject(Integer userId, String[] fillBlank, String[] judge, String[] multipleChoice) {
        Course course=(Course) map.get(userId);
        List<Boolean[]> fillBlankAnswerList=new ArrayList<>();
        /*获取答案*/
        List<MultipleChoice>listMul=course.getMultipleChoiceList();
        List<Judge>listJud=course.getJudgeList();
        List<FillBlank>listFil=course.getFillBlankList();

        /*初始化对比结果，true为答对，false为答错*/
        Boolean[]bMul=new Boolean[listMul.size()];
        Boolean[]bJud=new Boolean[listJud.size()];

        /*填空题打分并更新错题集*/
        for (int i=0;i<listFil.size();i++){

            /*处理前端和数据库填空题答案*/
            String[]strTrueAnswer=listFil.get(i).getAnswer().split(" ");
            String[]strAnSwer=fillBlank[i].split(" ",strTrueAnswer.length);
            /*初始化对比结果，true为答对，false为答错*/
            Boolean[]bFil=new Boolean[strTrueAnswer.length];
            /*比较答案*/
            boolean b=false;
            for (int j=0;j<strTrueAnswer.length;j++){
                bFil[j]=strTrueAnswer[j].equals(strAnSwer[j].trim());
                b=(bFil[j]&&b);
            }
            if (b==true){
                /*移出错题集*/
                    subjectRecordMapper.filOutOfRecord(userId,listFil.get(i).getFillBlankId());
            }else
            fillBlankAnswerList.add(bFil);
        }


        /*判断题打分并更新错题集*/
        if (judge!=null){
            for (int i=0;i<listJud.size();i++){
                bJud[i]=listJud.get(i).getAnswer().equals(judge[i]);
            if (bJud[i]==true){
                /*移出错题集*/
                subjectRecordMapper.judOutOfRecord(userId,listJud.get(i).getJudgeId());
            }
            }}else {
            for (int i=0;i<listJud.size();i++){
                /*用户答案为null，全部判为false*/
                bJud[i]=false;
            }
        }

        /*选择题打分并更新错题集*/
        if (multipleChoice!=null){
            for (int i=0;i<listMul.size();i++){
                bMul[i]=listMul.get(i).getAnswerTrue().equals(multipleChoice[i]);
            if (bMul[i]==true){
                /*移出错题集*/
                subjectRecordMapper.mulOutOfRecord(userId,listMul.get(i).getMultipleChoiceId());
            }
            }}else {
            for (int i=0;i<listMul.size();i++){
                /*用户答案为null，全部判为false*/
                bMul[i]=false;
            }
        }

//        /*填空题打分并更新错题集*/
//        for (int i=0;i<listFil.size();i++){
//            bFil[i]=listFil.get(i).getAnswer().equals(fillBlank[i]);
//            if (bFil[i]==true){
//                    subjectRecordMapper.filOutOfRecord(userId,listFil.get(i).getFillBlankId());
//            }
//        }
//        /*判断题打分并更新错题集*/
//        for (int i=0;i<listJud.size();i++){
//            bJud[i]=listJud.get(i).getAnswer().equals(judge[i]);
//            if (bJud[i]==true){
//                subjectRecordMapper.judOutOfRecord(userId,listJud.get(i).getJudgeId());
//            }
//        }
//        /*选择题打分并更新错题集*/
//        for (int i=0;i<listMul.size();i++){
//            bMul[i]=listMul.get(i).getAnswerTrue().equals(multipleChoice[i]);
//            if (bMul[i]==true){
//                subjectRecordMapper.mulOutOfRecord(userId,listMul.get(i).getMultipleChoiceId());
//            }
//        }
        Map<String,Object>ret=new HashMap<>();
        ret.put("fillBlank",fillBlankAnswerList);
        ret.put("judge",bJud);
        ret.put("multipleChoice",bMul);

        return ret;
    }

    @Override
    public List<Video> selectvideobyCourse(String courseName) {
        return courseMapper.selectvideobyCourse(courseName);
    }
}


