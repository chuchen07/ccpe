package cn.weblade.ccpe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class JiemainController {
    @GetMapping("/test")
    public String test(){
        return "temporary_page.html";
    }

    @GetMapping("/manage")
    public String manage(){
        return "manage.html";
    }

    @GetMapping("/main")
    public String main(){
        return "main.html";
    }

    @GetMapping("/course")
    public String course(){
        return "course.html";
    }

    @GetMapping("/answer")
    public String answer(){
        return "answer.html";
    }

    @GetMapping("/exam")
    public String exam(){
        return "exam.html";
    }
}
