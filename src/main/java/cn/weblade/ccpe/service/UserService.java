package cn.weblade.ccpe.service;

import cn.weblade.ccpe.dao.UserMapper;
import cn.weblade.ccpe.entity.User;
import cn.weblade.ccpe.utils.MailUtils;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Resource
    UserMapper userMapper;

    public boolean addUser(User user) throws MessagingException {
        String email = user.getEmail();
        MailUtils.sendMail(email,user.getUserCode());
        boolean addUser_result = userMapper.addUser(user);
        List<User> users = userMapper.findUserByEmail(email);
        Integer userId = users.get(0).getUserId();
        boolean addUserRole_result = userMapper.addUserRole(userId,1);
        return addUser_result && addUserRole_result;
    }


    public boolean activeUserState(String userCode){
        return userMapper.activeUserState(userCode);
    }

    public boolean checkUser(String email){

        List<User> user = userMapper.findUserByEmail(email);

        if(user.size()==0){
            return false;
        }else{
            return true;
        }
    }

    public List<User> findUserByEmail(String email){
        List<User> user = userMapper.findUserByEmail(email);
        return user;
    }


    public Set<String> findRoleByEmail(String email){
        Set<String> roles = userMapper.findRoleByEmail(email);
        return roles;
    }
    public Set<String> findPermissionByEmail(String email){
        Set<String> permissions = userMapper.findPermissionByEmail(email);
        return permissions;
    }


}
