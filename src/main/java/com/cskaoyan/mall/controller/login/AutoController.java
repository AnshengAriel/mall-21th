package com.cskaoyan.mall.controller.login;


import cn.hutool.core.util.StrUtil;
import com.cskaoyan.mall.bean.BaseResoVo;
import com.cskaoyan.mall.bean.login.InfoBean;
import com.cskaoyan.mall.realm.MallToken;
import com.cskaoyan.mall.service.login.AdminLoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/auth")
public class AutoController {

    @Autowired
    AdminLoginService adminLoginService;

    @RequestMapping("login")
    public BaseResoVo login(@RequestBody Map map) {

     /*   AdminBean adminBean = adminLoginService.queryAdmin(map);
        if(adminBean==null)return BaseResoVo.error(605,"用户帐号或密码不正确");

        return BaseResoVo.ok("a3d37c02-364c-4e48-8dc4-961d51741b2b");*/
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        MallToken adminToken = new MallToken(username,password,"admin");
        Subject subject = SecurityUtils.getSubject();
        subject.login(adminToken);
        Serializable id = subject.getSession().getId();
        return BaseResoVo.ok(id);
    }

    @RequestMapping("logout")
    public BaseResoVo logout() {

        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return BaseResoVo.ok();
    }

    @RequestMapping("info")
    public BaseResoVo info(String token){

       /* Subject subject = SecurityUtils.getSubject();
        Serializable id = subject.getSession().getId();
        System.out.println("id"+id);
        System.out.println("token"+token);

        HashMap<String, Object> data = new HashMap<>();
       // data.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        data.put("avatar","https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=247044218,2121766685&fm=26&gp=0.jpg");
        data.put("name","admin123");
        ArrayList<Object> perms = new ArrayList<>();
       *//* perms.add("GET /admin/brand/list");
        perms.add("GET /admin/category/list");*//*
        perms.add("*");
        ArrayList<Object> roles = new ArrayList<>();
        roles.add("超级管理员");
        data.put("perms",perms);
        data.put("roles",roles);
        return BaseResoVo.ok(data);*/

        Subject subject = SecurityUtils.getSubject();
        String adminUsername = (String) subject.getPrincipals().getPrimaryPrincipal();

        InfoBean infoBean = adminLoginService.queryByName(adminUsername);
        List<Integer> role_ids = infoBean.getRole_ids();
        List<String> roles = adminLoginService.queryRolenameById(role_ids);
        infoBean.setRoles(roles);
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
        infoBean.setPerms(strings);
        return BaseResoVo.ok(infoBean);
    }
}
