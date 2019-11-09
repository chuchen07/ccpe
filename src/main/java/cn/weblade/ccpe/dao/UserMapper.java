package cn.weblade.ccpe.dao;

import cn.weblade.ccpe.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface UserMapper {
    //注册一个用户
    public boolean addUser(User user);
    //根据用户激活码修改对应用户状态码
    public boolean activeUserState(String userCode);
    //验证邮箱是否存在
    public List<User> findUserByEmail(String email);
    //得到用户的所有角色
    public Set<String> findRoleByEmail(String email);
    //得到用户的所有权限
    public Set<String> findPermissionByEmail(String email);

}
