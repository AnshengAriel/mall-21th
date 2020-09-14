package com.cskaoyan.mall.realm;

import cn.hutool.core.util.StrUtil;
import com.cskaoyan.mall.bean.login.InfoBean;
import com.cskaoyan.mall.mapper.login.AdminMapper9;
import com.cskaoyan.mall.service.login.AdminLoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class AdminRealm extends AuthorizingRealm {

    @Autowired
    AdminMapper9 adminMapper9;
    @Autowired
    AdminLoginService adminLoginService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String adminUsername = (String) principalCollection.getPrimaryPrincipal();

        InfoBean infoBean = adminLoginService.queryByName(adminUsername);
        List<Integer> role_ids = infoBean.getRole_ids();
        List<String> perms = adminLoginService.queryPermissionById(role_ids);
        ArrayList<String> strings = new ArrayList<>();
        for (String perm : perms) {
            if(perm.equals("*"))strings.add("*");
            if(StrUtil.endWith(perm,"create")||StrUtil.endWith(perm,"delete")||StrUtil.endWith(perm,"update")
                    ||StrUtil.endWith(perm,"create")
            ){

                char s1=':';
                char s2='/';
                String replace = perm.replace(s1, s2);
                replace="POST /"+replace;

                strings.add(replace);
            }
            if(StrUtil.endWith(perm,"list")||StrUtil.endWith(perm,"read")||StrUtil.endWith(perm,"listRecord")
            ){

                char s1=':';
                char s2='/';
                String replace = perm.replace(s1, s2);
                replace="GET /"+replace;

                strings.add(replace);
            }
        }

        //List<String> permissions = adminMapper9.selectPermissionByUsername(username);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        simpleAuthorizationInfo.addStringPermissions(strings);
        return simpleAuthorizationInfo;
    }

   /* private List<String> queryPermissionByUser(String username) {

        ArrayList<String> perms = new ArrayList<>();
        perms.add("user:insert");
        perms.add("user:delete");
        return perms;
    }*/



    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        List<String> strings = adminMapper9.selectPasswordByName(username);
        String credential = strings.size()>=1?strings.get(0):"";
       // User user = new User(username, credential);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, credential, this.getName());
        return simpleAuthenticationInfo;

    }
}
