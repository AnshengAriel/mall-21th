package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.bean_of_hanyuandong.*;

import java.util.List;

public interface RoleService {

    List<RoleOptionVo> queryRoles();

    RoleListVo queryAllRole(Integer page, Integer limit, String sort, String order);

    RoleListVo queryRoleByName(Integer page, Integer limit, String sort, String order, String name);

    List<Role> queryRoleByName(String name);

    RoleCreateVo createRole(RoleCreateBo createBo);

    void updateRole(Role role);

    void logicDeleteRole(Role role);

    PermissionsVo getRolePermissions(Integer roleId);

    void setRolePermissions(RoleRermissionsBo permissions);
}
