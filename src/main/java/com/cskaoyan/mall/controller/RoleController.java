package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseRespVo;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.*;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.wx.Permission;
import com.cskaoyan.mall.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("admin/role/")
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping("options")
    public BaseRespVo options() {
        List<RoleOptionVo> roleOptionVos = roleService.queryRoles();
        return BaseRespVo.ok(roleOptionVos);
    }
    @RequestMapping("list")
    public BaseRespVo list(Integer page,Integer limit, String sort,String order) {
        RoleListVo roleListVo = roleService.queryAllRole(page,limit,sort,order);
        return BaseRespVo.ok(roleListVo);
    }

    @RequestMapping(value = "list",params = "name")
    public BaseRespVo search(Integer page,Integer limit, String sort,String order,String name) {
        RoleListVo roleListVo = roleService.queryRoleByName(page,limit,sort,order,name);
        return BaseRespVo.ok(roleListVo);
    }

    @RequestMapping("create")
    public BaseRespVo create(@RequestBody RoleCreateBo createBo) {
        List<Role> roleList = roleService.queryRoleByName(createBo.getName());
        if (roleList == null || roleList.size() == 0) {
            RoleCreateVo createVo = roleService.createRole(createBo);
            return BaseRespVo.ok(createBo);
        }
        return new BaseRespVo(null,"角色已经存在",640);
    }

    @PostMapping("update")
    public BaseRespVo update(@RequestBody Role role) {
        roleService.updateRole(role);
        return BaseRespVo.ok();
    }

    @PostMapping("delete")
    public BaseRespVo delete(@RequestBody Role role) {
        roleService.logicDeleteRole(role);
        return BaseRespVo.ok();
    }

    @GetMapping("permissions")
    public BaseRespVo getRolePermissions(Integer roleId) {
        PermissionsVo permissionsVo = roleService.getRolePermissions(roleId);
        return BaseRespVo.ok(permissionsVo);
    }

    @PostMapping("permissions")
    public BaseRespVo setRolePermissions(@RequestBody RoleRermissionsBo permissions) {

        roleService.setRolePermissions(permissions);
        return BaseRespVo.ok();
    }
}
