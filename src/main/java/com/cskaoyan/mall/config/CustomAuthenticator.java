package com.cskaoyan.mall.config;

import com.cskaoyan.mall.realm.MallToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;

public class CustomAuthenticator extends ModularRealmAuthenticator {
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {

        this.assertRealmsConfigured();
        Collection<Realm> originRealms = this.getRealms();
        //对realms来进行分发
        MallToken mallToken= (MallToken) authenticationToken;
        String type = mallToken.getType();
        ArrayList<Realm> realms = new ArrayList<>();
        for (Realm originRealm : originRealms) {
            if(originRealm.getName().toLowerCase().contains(type))realms.add(originRealm);
        }
        return realms.size() == 1 ? this.doSingleRealmAuthentication((Realm)realms.iterator().next(), authenticationToken)
                : this.doMultiRealmAuthentication(realms, authenticationToken);
    }
}
