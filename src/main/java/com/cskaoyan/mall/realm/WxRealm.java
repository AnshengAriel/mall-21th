package com.cskaoyan.mall.realm;

import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.mapper.login.AdminMapper9;
import com.cskaoyan.mall.mapper.login.UserMapper9;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class WxRealm extends AuthorizingRealm {

    @Autowired
    AdminMapper9 adminMapper9;
    @Autowired
    UserMapper9 userMapper9;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        ////////////////////////
        String username = (String) principalCollection.getPrimaryPrincipal();
        List<String> permissions = adminMapper9.selectPermissionByUsername(username);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        simpleAuthorizationInfo.addStringPermissions(permissions);
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
        List<String> strings = userMapper9.selectPasswordByName(username);
        String credential = strings.size()>=1?strings.get(0):"";
       // User user = new User(username, credential);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, credential, this.getName());
        return simpleAuthenticationInfo;

    }
}
