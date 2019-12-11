package cn.weblade.ccpe.controller;

import cn.weblade.ccpe.entity.Course;
import cn.weblade.ccpe.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;

    /*添加题*/
    @RequestMapping("/saveSubject")
    @ResponseBody
    public String subjectAdd(MultipartFile file) throws Exception {

        String ret=courseService.saveSubject(file);
        return ret;
    }

    /*删除题*/
    @RequestMapping("/deleteSubject")
    @ResponseBody
    public String subjectDelete(@RequestParam("paperName")String paperName){

        String ret=courseService.deleteSubject(paperName);
        return ret;
    }


    /*预览题，不添加进数据库*/
    @RequestMapping("/subjectBrowse")
    public Course subjectBrowse(MultipartFile file) throws Exception {

        Course ret=courseService.subjectBrowse(file);
        return ret;
    }
}
