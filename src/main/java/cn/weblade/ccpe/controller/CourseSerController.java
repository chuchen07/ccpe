package cn.weblade.ccpe.controller;

import cn.weblade.ccpe.entity.Course;
import cn.weblade.ccpe.entity.FillBlank;
import cn.weblade.ccpe.entity.Judge;
import cn.weblade.ccpe.entity.MultipleChoice;
import cn.weblade.ccpe.service.CourseSerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
public class CourseSerController {

    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView mv=new ModelAndView("user/upload");
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
    public List queryAllPaperName(){
        return  courseSerService.selectPaperName();
    }

    /*查找全部选择题*/
    @RequestMapping("/queryAllMultipleChoice")
    public List<MultipleChoice>queryAllMultipleChoice(){
        return  courseSerService.selectPaperName();
    }

    /*查找全部判断题*/
    @RequestMapping("/queryAllJudge")
    public List<Judge> queryAllJudge(){
        return  courseSerService.selectPaperName();
    }

    /*查找全部填空题*/
    @RequestMapping("/queryAllFillBlank")
    public List<FillBlank> queryAllFillBlank(){
        return  courseSerService.selectPaperName();
    }

    /*批改试卷*/
    @RequestMapping("/correctPaper")
    public Map<String,Object> correctPaper(Integer paperId,Integer userId,String[] fillBlank, String[]judge, String[]multipleChoice){
        paperId=44;
        userId=1;
        fillBlank=new String[10];
        fillBlank[0]="a";
        multipleChoice=new String[10];
        multipleChoice[0]="C";
        judge=new String[10];
        judge[0]="错";


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
        userId=1;
        fillBlank=new String[10];
        fillBlank[0]="a";
        multipleChoice=new String[10];
        multipleChoice[0]="C";
        judge=new String[10];
        judge[0]="错";

        Map<String,Object> ret=courseSerService.correctWrongSubject(userId,fillBlank,judge,multipleChoice);

        return  ret;
    }



}
