package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseResponseVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/auth")
public class AuthController {
    @RequestMapping("login")
    public BaseResponseVo login(@RequestBody Map map) {
        return BaseResponseVo.ok("dc5e9a08-e8b0-48d7-afaf-82d67d2d98fb");
    }
    @RequestMapping("info")
    public BaseResponseVo info(String token) {
        Map<String, Object> data = new HashMap<>();
        data.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        data.put("name", "admin123");
        List roles = new ArrayList();
        roles.add("超级管理员");
        List perms = new ArrayList();
        perms.add("*");
        data.put("roles", roles);
        data.put("perms", perms);
        return BaseResponseVo.ok(data);
    }
}
