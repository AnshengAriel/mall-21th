package com.cskaoyan.mall.controller.dashboard;


import com.cskaoyan.mall.bean.BaseResoVo;
import com.cskaoyan.mall.bean.dashBoard.DashBoardBean;
import com.cskaoyan.mall.service.dashBoard.DashBoardService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DashBoardController {

   @Autowired
   DashBoardService dashBoardService;

    @RequestMapping("admin/dashboard")
    public BaseResoVo count(){

        DashBoardBean dashBoardBean = dashBoardService.countDashBoard();

        return BaseResoVo.ok(dashBoardBean);
    }

    @RequestMapping("admin/profile/password")
    public BaseResoVo password(@RequestBody Map map){

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipals().getPrimaryPrincipal();
        int i = dashBoardService.updatePasswordByName(username, (String) map.get("oldPassword"),(String) map.get("newPassword"));
        if (i!=-1) return BaseResoVo.ok();
        return BaseResoVo.error(605,"密码不正确");
    }
}
