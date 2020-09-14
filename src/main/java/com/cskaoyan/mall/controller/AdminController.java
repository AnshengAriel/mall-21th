package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseRespVo;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.*;
import com.cskaoyan.mall.service.AdminService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/admin/")
public class AdminController {

    @Autowired
    AdminService adminService;

    //韩源东
    @RequestMapping("list")
    public BaseRespVo list(Integer page, Integer limit, String sort, String order) {
        AdminListVo adminList = adminService.queryAdmins(page, limit, sort, order);
        BaseRespVo baseRespVo = BaseRespVo.ok(adminList);
        return baseRespVo;
    }

    @RequestMapping(value = "list" ,params = "username")
    public BaseRespVo search(Integer page, Integer limit, String sort, String order,@Param("username") String username) {
        AdminListVo adminList = adminService.queryAdminsByUsername(page, limit, sort, order,username);
        BaseRespVo baseRespVo = BaseRespVo.ok(adminList);
        return baseRespVo;
    }

    @RequestMapping("create")
    public BaseRespVo create(@RequestBody AdminCreateBo createBo) {
        if (createBo.getPassword().length() < 6) {
            return new BaseRespVo(null,"管理员密码长度不能小于6",602);
        }
        List<Admin> admins = adminService.queryAdminsByUsername(createBo.getUsername());
        if (admins == null || admins.size() == 0){
            AdminCreateVo createVo = adminService.createAdmin(createBo.getUsername(), createBo.getPassword(), createBo.getAvatar(), createBo.getRoleIds());
            return BaseRespVo.ok(createVo);
        }
        return new BaseRespVo(null,"管理员已经存在",602);
    }

    @PostMapping("update")
    public BaseRespVo update(@RequestBody AdminUpdateBo updateBo){
        AdminUpdateVo updateVo = adminService.updateAdmin(updateBo);
        return BaseRespVo.ok(updateVo);
    }

    @PostMapping("delete")
    public BaseRespVo delete(@RequestBody AdminDeleteBo deleteBo){
        adminService.logicDeleteAdminById(deleteBo.getId());
        return BaseRespVo.ok();
    }
}


