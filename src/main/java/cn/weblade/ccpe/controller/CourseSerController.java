package cn.weblade.ccpe.controller;

import cn.weblade.ccpe.entity.*;
import cn.weblade.ccpe.service.CourseSerService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.org.apache.xerces.internal.xs.StringList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CourseSerController {



    @RequestMapping("/ajax")
    public ModelAndView ajax(){
        ModelAndView mv=new ModelAndView("/ajax.html");
        return mv;
    }
    @RequestMapping("/allSubject")
    public ModelAndView querySubject(){
        ModelAndView mv=new ModelAndView("/allSubject.html");
        return mv;
    }
    @RequestMapping("/h5")
    public ModelAndView h5(){
        ModelAndView mv=new ModelAndView("/h5");
        return mv;
    }

    @RequestMapping("/answerSubject")
    public ModelAndView answerSubject(){
        ModelAndView mv=new ModelAndView("/answerSubject.html");
        return mv;
    }

    @RequestMapping("/answerWrongSubject")
    public ModelAndView answerWrongSubject(){
        ModelAndView mv=new ModelAndView("/answerWrongSubject.html");
        return mv;
    }
    @Autowired
    private CourseSerService courseSerService;




     /*查找题*/
    @RequestMapping("/querySubject")
   @ResponseBody
    public Course querySubject(@RequestParam("paperId")Integer paperId,Model model){

        Course course=courseSerService.querySubject(paperId);
        model.addAttribute("course",course);
        return course;
    }

//    /*查找全部试卷名称*/
//    @RequestMapping("/queryAllPaperName")
//    public List<Paper> queryAllPaperName(){
//        List<Paper>list=courseSerService.selectPaperName();
//
//        return  list;
//
//    }

    /*查找全部试卷名称*/
    @RequestMapping("/queryAllPaperName")
    @ResponseBody
    public List<Paper> queryAllPaperName(Model model){
        List<Paper>list=courseSerService.selectPaperName();
//        model.addAttribute("lists",list);
//        String str="/ajax.html";
        model.addAttribute("list", list);
        return  list;

    }

    /*查找全部选择题*/
    @RequestMapping("/queryAllMultipleChoice")
    @ResponseBody
    public List<MultipleChoice>queryAllMultipleChoice(){
        return  courseSerService.queryAllMultipleChoice();
    }

    /*查找全部判断题*/
    @RequestMapping("/queryAllJudge")
    @ResponseBody
    public List<Judge> queryAllJudge(){
        return  courseSerService.queryAllJudge();
    }

    /*查找全部填空题*/
    @RequestMapping("/queryAllFillBlank")
    @ResponseBody
    public List<FillBlank> queryAllFillBlank(){
        return  courseSerService.queryAllFillBlank();
    }

    /*批改试卷*/
    @RequestMapping(value = "/correctPaper")
    @ResponseBody
    public Map<String,Object> correctPaper(Integer paperId, Integer userId, String[] fillBlankAnswer, String[]judgeAnswer, String[]multipleChoiceAnswer){
//        paperId=44;
//        userId=1;
//        fillBlank=new String[10];
//        fillBlank[0]="a";
//        multipleChoice=new String[10];
//        multipleChoice[0]="C";
//        judge=new String[10];
//        judge[0]="错";


        Map<String,Object>map=courseSerService.correctPaper(paperId,userId,fillBlankAnswer,judgeAnswer,multipleChoiceAnswer);

        return  map;
    }

    /*查找错题集*/

    @RequestMapping("/queryAllRecordSubject")
    @ResponseBody
    public Course queryAllRecordSubject(Integer userId){

        Course course=courseSerService.queryAllRecordSubject(userId);
        return  course;
    }

    /*错题集批改*/
    @RequestMapping("/correctWrongSubject")
    @ResponseBody
    public Map<String,Object> correctWrongSubject(Integer userId, String[] fillBlankAnswer, String[]judgeAnswer, String[]multipleChoiceAnswer){
//        userId=1;
//        fillBlank=new String[10];
//        fillBlank[0]="a";
//        multipleChoice=new String[10];
//        multipleChoice[0]="C";
//        judge=new String[10];
//        judge[0]="错";

        Map<String,Object> ret=courseSerService.correctWrongSubject(userId,fillBlankAnswer,judgeAnswer,multipleChoiceAnswer);

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
