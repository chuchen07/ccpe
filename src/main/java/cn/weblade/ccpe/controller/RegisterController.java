package cn.weblade.ccpe.controller;

import cn.weblade.ccpe.entity.User;
import cn.weblade.ccpe.service.UserService;
import cn.weblade.ccpe.utils.MD5Utils;
import cn.weblade.ccpe.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;


@Controller
public class RegisterController {
    @Autowired
    UserService userService;

    @GetMapping("/registerPage")
    public String registPage(){
        return "register.html";
    }


    @PostMapping("/register")
    public String regist(User userForm, Model model) {
        User user = new User();
        String email = userForm.getEmail();
        user.setEmail(email);
        String passWord = userForm.getPassWord();
        String encryptyPassword = MD5Utils.encript_password(passWord,email);
        user.setPassWord(encryptyPassword);
        user.setUserName(userForm.getUserName());
        user.setUserState("0");
        //64位的激活码，所以获取了两次
        String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
        user.setUserCode(code);

        try {
            userService.addUser(user);
            model.addAttribute("msg","注册成功，快到邮箱激活吧");

        } catch (MessagingException e) {

            model.addAttribute("msg","注册失败"+e);
        }

        return "register_result.html";
    }
    @GetMapping("/loginPage")
    public String loginPage(){
        return "login.html";
    }


    @GetMapping("/registerCheck")
    public String registerCheck(String code){
        boolean result = userService.activeUserState(code);
        if(result){
            return "login.html";
        }else{
            return "login_false.html";
        }

    }

    @ResponseBody
    @GetMapping("/emailcheck")
    public boolean emailcheck(String email){
      return userService.checkUser(email);
    }

    //TODO暂且使用

    @GetMapping("/add")
    public String temporary(){
        return  "temporary_page.html";
    }

}
