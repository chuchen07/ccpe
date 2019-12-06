package cn.weblade.ccpe.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

//shiro配置类
@Configuration
public class ShiroConfig {
    /*
    1。创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //1.设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加shiro内置过滤器
        /**
         * 常用的过滤器：
         *  anon:无需认证既可以登录
         *  authc：必须认证才可以登录
         *  user：如果使用rememberMe的功能可以直接访问
         *  perms:该资源必须得到资源权限才可以访问
         *  role:该资源必须得到角色权限才可以访问
         */
        Map<String,String> filterMap = new LinkedHashMap<String,String>();

        filterMap.put("/index.html","anon");

        //TODO
        // 待删deleteUser
        filterMap.put("/updateUser","anon");
       // filterMap.put("/deleteUser","anon");
        filterMap.put("/answer","anon");
        filterMap.put("/course","anon");
        filterMap.put("/main","anon");
        filterMap.put("/manage","anon");
        filterMap.put("/test","anon");
        filterMap.put("/data","anon");
        filterMap.put("/index","anon");
        filterMap.put("/emailcheck","anon");
        filterMap.put("/login","anon");
        filterMap.put("/registerPage","anon");
        filterMap.put("/register","anon");
        filterMap.put("/registerCheck","anon");
        filterMap.put("/static/*","anon");
        filterMap.put("/logout","logout");
        filterMap.put("/subjectBrowse","anon");
        filterMap.put("/upload","anon");
        filterMap.put("/*","authc");

       //设置未授权界面
      shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorize_Page");
       shiroFilterFactoryBean.setLoginUrl("/loginPage");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    /*
    2.创建DefaultWebSecuritiyMannager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /*
    3.创建Realm
     */
    @Bean(name = "userRealm")
    public UserRealm getUserRealm(){
        UserRealm userRealm = new UserRealm();
        //使用shiro安全加密
        HashedCredentialsMatcher matcher =  new HashedCredentialsMatcher();
        //设置加密的算法
        matcher.setHashAlgorithmName("MD5");
        //设置加密次数
        matcher.setHashIterations(1024);
        userRealm.setCredentialsMatcher(matcher);

        return userRealm;
    }
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
