package cn.weblade.ccpe.dao;

import cn.weblade.ccpe.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface UserMapper {
    //注册一个用户
    //1.添加用户进入user表
    //2.添加用户user_role表，赋予普通用户权限
    public boolean addUser(User user);
    public boolean addUserRole(Integer userId,Integer rid);
    //根据用户激活码修改对应用户状态码
    public boolean activeUserState(String userCode);
    //根据邮箱获取用户
    public List<User> findUserByEmail(String email);
    //得到用户的所有角色
    public Set<String> findRoleByEmail(String email);
    //得到用户的所有权限
    public Set<String> findPermissionByEmail(String email);


}
