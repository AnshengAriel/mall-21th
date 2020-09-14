package com.cskaoyan.mall.config;

import com.cskaoyan.mall.realm.AdminRealm;
import com.cskaoyan.mall.realm.WxRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //登录和认证失败重定向的url
        //shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        //shiroFilterFactoryBean.setLoginUrl("/admin/auth/login");
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        filterMap. put("/user/9","anon");
        filterMap. put("/user/9/druid","anon");
        filterMap. put("/druid","anon");
        //filterMap.put("/#/login","anon");

        filterMap. put("/wx/auth/regCaptcha","anon");
        filterMap. put("/wx/auth/register","anon");
        filterMap. put("/wx/auth/reset","anon");
        filterMap. put("/wx/storage/upload","anon");

        filterMap. put("/admin/auth/login","anon");
        filterMap. put("/admin/auth/logout","anon");
        filterMap. put("/*.png","anon");
        filterMap. put("/wx/search/helper","anon");

        filterMap. put("/wx/auth/login","anon");
        filterMap. put("/wx/auth/login_by_weixin","anon");
        filterMap. put("/wx/auth/logout","anon");

//        filterMap. put("/admin/brand/creat","anon");
//        filterMap. put("/admin/brand/update","anon");
       // filterMap. put("/wx/user/index","anon");
        filterMap. put("/wx/storage/upload","anon");
        filterMap. put("/wx/catalog/index","anon");
        filterMap. put("/wx/home/index","anon");
        filterMap. put("/wx/goods/count","anon");
        filterMap. put("/wx/goods/category","anon");
        filterMap. put("/wx/brand/list","anon");
        filterMap. put("/wx/brand/detail","anon");
        //filterMap. put("/admin/region/list","perms[GET /admin/region/list]");
        //filterMap.put("/wx/auth/login","anon");
       // filterMap.put("/unAuthc","anon");
        //当你分配了perm1的权限才能访问need/perm这个请求
       // filterMap.put("/need/perm","perms[perm1]");
        //filterMap.put("/auth/logout","logout");
        //filterMap.put("/admin/auth/logout","logout");

        filterMap.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * shiro核心组件securityManager
     * @param adminRealm
     * @param defaultWebSessionManager
     * @return
     */

    @Bean
    public DefaultWebSecurityManager securityManager(AdminRealm adminRealm,
                                                     WxRealm wxRealm,
                                                     DefaultWebSessionManager defaultWebSessionManager,
                                                     CustomAuthenticator authenticator){

        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        ArrayList<Realm> realms = new ArrayList<>();
        realms.add(adminRealm);
        realms.add(wxRealm);
        defaultWebSecurityManager.setRealms(realms);
       // defaultWebSecurityManager.setRealms();
      //  defaultWebSecurityManager.setSessionManager(webSessionManager());
        defaultWebSecurityManager.setSessionManager(defaultWebSessionManager);
        defaultWebSecurityManager.setAuthenticator(authenticator);
        return defaultWebSecurityManager;
    }


    /**
     * 声明式鉴权 注解需要的组件
     */

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){

        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    /**
     * 使用映射处理异常:key为异常全类名，value为异常处理的请求
     */

   /* public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){

        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.put("org.apache.shiro.authz.AuthorizationException","/noperm");
        simpleMappingExceptionResolver.setExceptionMappings(mappings);
        return simpleMappingExceptionResolver;
    }*/

     /*
            不需重新进入realm
         */
   @Bean
    public DefaultWebSessionManager webSessionManager(){

       CustomSessionManager customSessionManager = new CustomSessionManager();
       //customSessionManager.setGlobalSessionTimeout(99999999);
       return customSessionManager;
   }


    /**
     * 分发admin，vx
     */
    @Bean
    public CustomAuthenticator authenticator(AdminRealm adminRealm, WxRealm wxRealm){

        CustomAuthenticator customAuthenticator = new CustomAuthenticator();
        ArrayList<Realm> realms = new ArrayList<>();
        realms.add(adminRealm);
        realms.add(wxRealm);
        customAuthenticator.setRealms(realms);
        return customAuthenticator;
    }
}
