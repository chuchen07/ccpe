package cn.weblade.ccpe.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @PostMapping("/login")
    public String login(String email, String passWord, Model model){
        /*
        使用shiro编写认证操作
         */

        //1.获取subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(email,passWord);
        //3.执行登录方法,只要出现任何异常，就是登录失败
        try {
            subject.login(token);
            //登录成功
        } catch (UnknownAccountException e) {
            //登录失败:用户名不存在
            model.addAttribute("msg","用户名不存在或未激活");
            return "login.html";

        }catch (IncorrectCredentialsException e){
            //登录失败：密码错误
            model.addAttribute("msg","用户密码错误");
            return "login.html";
        }catch (AuthenticationException e){
            model.addAttribute("msg","其他登录错误");
            return "login.html";
        }

        return "index.html";
    }
    //TODO
    @GetMapping("/index")
    public String index(){
        return "index.html";
    }
}
