package cn.weblade.ccpe.controller;

import cn.weblade.ccpe.entity.Page;
import cn.weblade.ccpe.entity.User;
import cn.weblade.ccpe.entity.rolepermission;
import cn.weblade.ccpe.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

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
            model.addAttribute("msg","用户名不存在或未激活");
            return "login.html";
        }
        if(subject.hasRole("管理员")){
            return "manage.html";
        }else if(subject.hasRole("普通用户")){
            return "main.html";
        }
        return "unauthorize_Page.html";
    }
    //TODO
    @GetMapping("/index")
    public String index(){
        return "index.html";
    }


    /**
     * @param currentPage 当前页码
     * @param pageAmount 页面数量
     * @return  返回Page对象
     */
    @ResponseBody
    @GetMapping("/data")
    public Page<User> getPageUserMessages(@RequestParam("currentPage")Integer currentPage,
                                          @RequestParam("pageAmount")Integer pageAmount)
    {
       Page<User>page =  userService.getPageUserMessages(pageAmount,currentPage);
       if(page==null){
           Page<User> erropage = new Page<User>();
           erropage.setMsg("请求参数无效");
           return erropage;
       }
       page.setMsg("请求成功");
       return page;
    }

    /**
     * @param email 邮箱
     * @return   如果删除成功返回true；否则返回false；
     */
    @ResponseBody
    @GetMapping("/deleteUser")
    public boolean deleteUser(@RequestParam("email")String email){
       return userService.deleteUser(email);
    }

    @ResponseBody
    @PostMapping("/updateUser")
    public boolean updateUser(User user){
        return userService.updateUser(user);
    }

    @ResponseBody
    @GetMapping("/getAllRolePermission")
    public List<rolepermission> getAllRolePermission(){
        return userService.getAllRolePermission();
    }

}
