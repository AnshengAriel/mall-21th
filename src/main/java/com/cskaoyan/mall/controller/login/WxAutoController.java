package com.cskaoyan.mall.controller.login;


import cn.hutool.core.util.StrUtil;
import com.cskaoyan.mall.bean.BaseResoVo;
import com.cskaoyan.mall.bean.login.*;
import com.cskaoyan.mall.realm.MallToken;
import com.cskaoyan.mall.service.login.AdminLoginService;
import com.cskaoyan.mall.service.login.WxLoginService;
import com.cskaoyan.mall.service.test_jiayi.FileService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.*;

@RestController
@RequestMapping("wx")
public class WxAutoController {

    private String code1;
    private String mobile1;
    private  Integer i=0;

    @Autowired
    AdminLoginService adminLoginService;
    @Autowired
    WxLoginService wxLoginService;
    @Autowired
    FileService fileService;

    @RequestMapping("auth/login")
    public BaseResoVo login(@RequestBody Map map) {

     /*   AdminBean adminBean = adminLoginService.queryAdmin(map);
        if(adminBean==null)return BaseResoVo.error(605,"用户帐号或密码不正确");

        return BaseResoVo.ok("a3d37c02-364c-4e48-8dc4-961d51741b2b");*/
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        MallToken wxToken = new MallToken(username,password,"wx");
        Subject subject = SecurityUtils.getSubject();
        subject.login(wxToken);
        Serializable id = subject.getSession().getId();


        Date startTimestamp = subject.getSession().getStartTimestamp();
        Calendar c = Calendar.getInstance();
        c.setTime(startTimestamp);
        c.add(Calendar.MINUTE, 1440);
        Date time = c.getTime();


        UserInfo userInfo = wxLoginService.querytInfoByName(username);
        WxUser wxUser = new WxUser();
        wxUser.setToken(id);
        wxUser.setTokenExpire(time);
        wxUser.setUserInfo(userInfo);
        return BaseResoVo.ok(wxUser);
    }

    @RequestMapping("auth/login_by_weixin")
    public BaseResoVo login_by_weixin() {

        return BaseResoVo.error(-1,"错误");
    }



    @RequestMapping("auth/regCaptcha")
    public BaseResoVo authRegCaptcha(@RequestBody Mobile mobile) {

        String mobile9 = mobile.getMobile();
        String code = vcode();
        code1=code;
        mobile1=mobile9;

        if(mobile9.length()!=11)return BaseResoVo.error(701,"手机号不正确");

        fileService.sendMsg(mobile9,code);
        i++;
        return BaseResoVo.ok();
    }

    public static String vcode(){
        String vcode = "";
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int)(Math.random() * 9);
        }
        return vcode;
    }

    @RequestMapping("auth/reset")
    public BaseResoVo reset(@RequestBody Reset reset) {


        if (i==0)return BaseResoVo.error(701,"请获取验证码");
        if (!reset.getMobile().equals(mobile1))return BaseResoVo.error(701,"两次手机号不一致");
        if(!reset.getCode().equals(code1))return BaseResoVo.error(701,"验证码错误");

        Integer integer = wxLoginService.resetPassword(reset.getMobile(), reset.getPassword());
        if(integer==0)return BaseResoVo.error(701,"该手机号未注册");
        else return BaseResoVo.ok("密码重置成功");
    }


    @RequestMapping("auth/register")
    public BaseResoVo register(@RequestBody Register register) {

        if (i==0)return BaseResoVo.error(701,"请获取验证码");
        if (!register.getMobile().equals(mobile1))return BaseResoVo.error(701,"两次手机号不一致");
        if(!register.getCode().equals(code1))return BaseResoVo.error(701,"验证码错误");

        Integer integer = wxLoginService.selectCount(register.getUsername());
        if(integer==0){
            wxLoginService.insertUser(register.getUsername(),register.getPassword(),register.getMobile());
            HashMap<String, String> map = new HashMap<>();
            String username = register.getUsername();
            String password = register.getPassword();
            /*map.put("username",username);
            map.put("password",password);
            login(map);*/
            MallToken wxToken = new MallToken(username,password,"wx");
            Subject subject = SecurityUtils.getSubject();
            subject.login(wxToken);
            Serializable id = subject.getSession().getId();
            Date startTimestamp = subject.getSession().getStartTimestamp();
            Calendar c = Calendar.getInstance();
            c.setTime(startTimestamp);
            c.add(Calendar.MINUTE, 1440);
            Date time = c.getTime();


            UserInfo userInfo = wxLoginService.querytInfoByName(username);
            WxUser wxUser = new WxUser();
            wxUser.setToken(id);
            wxUser.setTokenExpire(time);
            wxUser.setUserInfo(userInfo);
            return BaseResoVo.ok(wxUser);

            //return BaseResoVo.ok("恭喜您注册成功");
        }
        else {
            return BaseResoVo.error(701,"用户名已存在");
        }
    }



    @RequestMapping("auth/logout")
    public BaseResoVo logout() {

        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return BaseResoVo.error(501,"请登录");
    }

    @RequestMapping("user/index")
    public BaseResoVo userindex() {
        //写着两句获取你当前的用户的username,拿这个参数去查你想要的信息
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipals().getPrimaryPrincipal();

        Order order = wxLoginService.queryOrderCount(username);
        Base<Order> orderBase = new Base<>();
        orderBase.setOrder(order);
        return BaseResoVo.ok(orderBase);

        // return "{\"errno\":0,\"data\":{\"order\":{\"unrecv\":0,\"uncomment\":1,\"unpaid\":3,\"unship\":1}},\"errmsg\":\"成功\"}";
    }

    //@RequestMapping("info")
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
