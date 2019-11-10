package cn.weblade.ccpe.config;

import cn.weblade.ccpe.entity.User;
import cn.weblade.ccpe.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    /*
    执行授权逻辑
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取凭证邮箱
        String email = (String)principalCollection.getPrimaryPrincipal();

        //获取用户角色和权限
        Set<String> roles = userService.findRoleByEmail(email);
        Set<String> permissions = userService.findPermissionByEmail(email);
        //创建一个授权对象
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();

        s.setStringPermissions(permissions);
        s.setRoles(roles);
        return s;
    }

    /*
    执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //执行认证逻辑
        //1.获取用户输入的账号
        String email = (String)authenticationToken.getPrincipal();
        //从数据库里面获取用户信息，例子提示说这里可以做缓存,如果不做，shiro有自己的缓存机制，两分钟内不会重复验证
        List<User> user = userService.findUserByEmail(email);
        //如果用户数量为0,即用户不存在（这里应该有更好的机制，但是数据库方面没有做限制，所以不敢保证邮箱的唯一性）
        String state = user.get(0).getUserState();
        int num = user.size();
       if(num==0 || state.equals("0")){
           return null;
           //TODO：bug：无法抛出unknowAccoutExceptioon
         }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.get(0).getEmail(),user.get(0).getPassWord(),getName());

        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(email));
        return authenticationInfo;
    }


}
