package com.cskaoyan.mall.controller.login;/*
package com.cskaoyan.mall.controller.login;


import com.cskaoyan.mall.bean.BaseResoVo;
import com.cskaoyan.mall.bean.login.AdminBean;
import com.cskaoyan.mall.realm.MallToken;
import com.cskaoyan.mall.service.login.AdminLoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AutoController9 {


    @RequestMapping(value = "/index")
    public String login(@RequestBody Map map) {

        return "index";
    }


    @RequestMapping(value = "unAuthc")
    public String unAuthc() {

        return "forward:/index";
    }

    *
     * 认证
     * @param username
     * @param password
     * @return


    @RequestMapping(value = "auth/login")
    @ResponseBody
    public String login(String username,String password) {

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username,password));
        } catch (AuthenticationException e) {

            return "forward:/index";
            // e.printStackTrace();
        }

        Serializable id = subject.getSession().getId();
        return (String) id;
    }

    *
     * 在认证之前先访问success
     * 认证通过之后在访问success
     * @return


    @RequestMapping(value = "success")
    public String success() {

        return "success";
    }



    @RequestMapping(value = "need/perm")
    @RequiresPermissions(value = {"perm1","perm2"},logical = Logical.OR)
    public String needPerm() {

        Subject subject = SecurityUtils.getSubject();
        //subject.getPrincipal()
        Object primaryPrincipal = subject.getPrincipals().getPrimaryPrincipal();
        return "permission";
    }


    @RequestMapping(value = "noperm")
    public String noperm() {

        return "noperm";
    }

    @RequestMapping(value = "auth/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "/index";
    }


    //---------------------------------------------------------------------------------------------------------
    @RequestMapping("admin/login")
    public String adminLogin(String username,String password){

        MallToken adminToken = new MallToken(username, password, "admin");
        Subject subject = SecurityUtils.getSubject();
        subject.login(adminToken);
        return "success";
    }

    @RequestMapping("wx/login")
    public String wxLogin(String username,String password){

        MallToken wxToken = new MallToken(username, password, "wx");
        Subject subject = SecurityUtils.getSubject();
        subject.login(wxToken);
        return "success";
    }
}
*/
