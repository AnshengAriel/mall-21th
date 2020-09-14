/*
package com.cskaoyan.mall.controller;


import com.cskaoyan.mall.bean.BaseRespVo;
import com.cskaoyan.mall.bean.InfoVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * 登录
 *//*

@RestController
@RequestMapping("admin9/auth9/")
public class AuthController9 {
    @RequestMapping("login")
    public BaseRespVo login() {

        */
/**
         * 登录逻辑暂时未写
         *//*

        BaseRespVo baseRespVo = new BaseRespVo("69dbc33c-99fe-4bf1-8d66-dccd8195d9c9", "成功", 0);
        return baseRespVo;
    }

    @RequestMapping("info")
    public BaseRespVo info(String token) {
        */
/**
         * 显示信息的具体的逻辑还没有写
         *//*


        List<String> roles = new ArrayList<>();
        List<String> perms = new ArrayList<>();
        roles.add("超级管理员");
        perms.add("*");
        InfoVo data = new InfoVo(roles, "admin123", perms, "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        BaseRespVo baseRespVo = BaseRespVo.ok(data);
        return baseRespVo;
    }

    @RequestMapping("/dashboard")
    public Object dashboardInfo(){
        return null;
    }

}
*/
