package com.cskaoyan.mall.realm;

import com.cskaoyan.mall.bean.login.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

public class CustomRealm1 extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User primaryPrincipal = (User) principalCollection.getPrimaryPrincipal();
        List<String> permissions = queryPermissionByUser(primaryPrincipal.getUsername());

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole("role1");
        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    private List<String> queryPermissionByUser(String username) {

        ArrayList<String> perms = new ArrayList<>();
        perms.add("user:insert");
        perms.add("user:delete");
        return perms;
    }





    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String passwordFromDb = queryPasswordByUsername(username);
        User user = new User(username, passwordFromDb);

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, passwordFromDb, this.getName());
        return simpleAuthenticationInfo;

    }

    private String queryPasswordByUsername(String username) {
        return "123456";
    }
}
