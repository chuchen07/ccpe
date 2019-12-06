package cn.weblade.ccpe.dao;

import cn.weblade.ccpe.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Set;

@Mapper
public interface UserMapper {
    //注册一个用户
    //1.添加用户进入user表
    //2.添加用户user_role表，赋予普通用户权限
    public boolean addUser(User user);
    public boolean addUserRole(@Param("userId") Integer userId,@Param("rid") Integer rid);
    //根据用户激活码修改对应用户状态码
    public boolean activeUserState(String userCode);
    //根据邮箱获取用户
    public List<User> findUserByEmail(String email);
    //得到用户的所有角色
    public Set<String> findRoleByEmail(String email);
    //得到用户的所有权限
    public Set<String> findPermissionByEmail(String email);

    /**
     * @param startLineNum 起始条目数
     * @param pageAmount 每次条目数
     * @return
     */
    //获取部分用户角色（主要用于分页）
    public List<User> getPartyUsers (@Param("startLineNum")Integer startLineNum,@Param("pageAmount") Integer pageAmount);
    //获取用户数量（主要用于分页总条数）
    public Integer getUsersAmount();

    //删除用户
    //通过邮箱删除用户
    //通过用户ID删除用户角色表对应用户
    public boolean deleteUser(@Param("email")String email);
    public boolean deleteUserRole(@Param("userId")Integer userId);

    //更新用户信息
    public boolean updateUser(User user);

    //获取权限列表

}
