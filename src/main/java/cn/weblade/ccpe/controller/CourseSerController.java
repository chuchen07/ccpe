package cn.weblade.ccpe.controller;

import cn.weblade.ccpe.entity.*;
import cn.weblade.ccpe.service.CourseSerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class CourseSerController {



    @RequestMapping("/exam")
    public ModelAndView exam(){
        ModelAndView mv=new ModelAndView("/exam");
        return mv;
    }
    @RequestMapping("/h5")
    public ModelAndView h5(){
        ModelAndView mv=new ModelAndView("/h5");
        return mv;
    }


    @Autowired
    private CourseSerService courseSerService;




     /*查找题*/
    @RequestMapping("/querySubject")
    public Course querySubject(String paperName){
        paperName="C++模拟题";

        Course course=courseSerService.querySubject(paperName);
        System.out.println(course);
        return course;
    }

    /*查找全部试卷名称*/
    @RequestMapping("/queryAllPaperName")
    @ResponseBody
    public List<Paper> queryAllPaperName(Model model){
        List<Paper>list=courseSerService.selectPaperName();
        model.addAttribute("list", list);
        return  list;

    }

    /*查找全部选择题*/
    @RequestMapping("/queryAllMultipleChoice")
    public List<MultipleChoice>queryAllMultipleChoice(){
        return  courseSerService.queryAllMultipleChoice();
    }

    /*查找全部判断题*/
    @RequestMapping("/queryAllJudge")
    public List<Judge> queryAllJudge(){
        return  courseSerService.queryAllJudge();
    }

    /*查找全部填空题*/
    @RequestMapping("/queryAllFillBlank")
    public List<FillBlank> queryAllFillBlank(){
        return  courseSerService.queryAllFillBlank();
    }

    /*批改试卷*/
    @RequestMapping("/correctPaper")
    public Map<String,Object> correctPaper(Integer paperId,Integer userId,String[] fillBlank, String[]judge, String[]multipleChoice){
//        paperId=44;
//        userId=1;
//        fillBlank=new String[10];
//        fillBlank[0]="a";
//        multipleChoice=new String[10];
//        multipleChoice[0]="C";
//        judge=new String[10];
//        judge[0]="错";


        Map<String,Object>map=courseSerService.correctPaper(paperId,userId,fillBlank,judge,multipleChoice);

        return  map;
    }

    /*查找错题集*/
    @RequestMapping("/queryAllRecordSubject")
    public Course queryAllRecordSubject(Integer userId){

        Course course=courseSerService.queryAllRecordSubject(userId);
        return  course;
    }

    /*错题集批改*/
    @RequestMapping("/correctWrongSubject")
    public Map<String,Object> correctWrongSubject(Integer userId,String[] fillBlank, String[]judge, String[]multipleChoice){
//        userId=1;
//        fillBlank=new String[10];
//        fillBlank[0]="a";
//        multipleChoice=new String[10];
//        multipleChoice[0]="C";
//        judge=new String[10];
//        judge[0]="错";

        Map<String,Object> ret=courseSerService.correctWrongSubject(userId,fillBlank,judge,multipleChoice);

        return  ret;
    }


   //测试播放
    @RequestMapping("/video")
    public ModelAndView watchvideo(String courseName){
        ModelAndView model= new ModelAndView();
        model.addObject("videoback",courseSerService.selectvideobyCourse(courseName));
        model.setViewName("watchVideo");
        return model;
    }



}
