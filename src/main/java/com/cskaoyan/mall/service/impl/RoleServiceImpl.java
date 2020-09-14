package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.bean_of_hanyuandong.*;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.wx.*;
import com.cskaoyan.mall.mapper.FunctionMapper;
import com.cskaoyan.mall.mapper.PermissionActualMapper;
import com.cskaoyan.mall.mapper.PermissionMapper;
import com.cskaoyan.mall.mapper.RoleMapper;
import com.cskaoyan.mall.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    PermissionActualMapper permissionActualMapper;

    @Autowired
    FunctionMapper functionMapper;


    @Override
    public List<RoleOptionVo> queryRoles() {
        List<RoleOptionVo> roleOptionVos = roleMapper.selectRoles();
        return roleOptionVos;
    }

    @Override
    public RoleListVo queryAllRole(Integer page, Integer limit, String sort, String order) {
        //先分页
        PageHelper.startPage(page,limit);
        //设置一个排序的条件
        RoleExample roleExample = new RoleExample();
        roleExample.setOrderByClause(sort + " " + order);
        roleExample.createCriteria().andDeletedEqualTo(false);
        //根据条件查询
        List<Role> roles = roleMapper.selectByExample(roleExample);
        //获取总条目数
        PageInfo<Role> pageInfo = new PageInfo<>(roles);
        long total = pageInfo.getTotal();
        //封装结果
        RoleListVo roleListVo = new RoleListVo();
        roleListVo.setItems(roles);
        roleListVo.setTotal(total);
        return roleListVo;
    }

    @Override
    public RoleListVo queryRoleByName(Integer page, Integer limit, String sort, String order, String name) {
        //分页
        PageHelper.startPage(page,limit);
        //条件
        RoleExample roleExample = new RoleExample();
        roleExample.setOrderByClause(sort + " " + order);
        roleExample.createCriteria().andNameLike("%" + name + "%");
        roleExample.createCriteria().andDeletedEqualTo(false);

        List<Role> roles = roleMapper.selectByExample(roleExample);
        PageInfo<Role> pageInfo = new PageInfo<>(roles);
        long total = pageInfo.getTotal();

        //封装结果
        RoleListVo roleListVo = new RoleListVo();
        roleListVo.setItems(roles);
        roleListVo.setTotal(total);
        return roleListVo;
    }

    @Override
    public List<Role> queryRoleByName(String name) {
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andNameEqualTo(name);
        List<Role> roles = roleMapper.selectByExample(roleExample);
        return roles;
    }

    @Override
    public RoleCreateVo createRole(RoleCreateBo createBo) {
        Date date = new Date();
        Role role = new Role(null, createBo.getName(), createBo.getDesc(), false, date, date, false);
        roleMapper.insert(role);
        //根据名字查询封装成VO返回
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andNameEqualTo(createBo.getName());
        List<Role> roles = roleMapper.selectByExample(roleExample);
        Role role1 = roles.get(0);
        RoleCreateVo createVo = new RoleCreateVo(role1.getId(), role1.getName(), role1.getDesc(), role1.getAddTime(), role1.getUpdateTime());
        return createVo;
    }

    @Override
    public void updateRole(Role role) {
        role.setUpdateTime(new Date());
        roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public void logicDeleteRole(Role role) {
        role.setDeleted(true);
        roleMapper.updateByPrimaryKey(role);
    }

    //这是哪个沙雕写的代码，跟s一样.哦！原来是我自己555
    @Override
    public PermissionsVo getRolePermissions(Integer roleId) {
        //先根据roleId查询Permission表，看看这个角色与那些权限
        List<String> permissions = permissionMapper.selectPermissionsByRoleId(roleId);
        //查询所有的子功能
        FunctionExample functionExample = new FunctionExample();
        functionExample.createCriteria().andPidNotEqualTo(0);
        List<Function> functions = functionMapper.selectByExample(functionExample);
        //查询所有权限
        List<PermissionActual> permissionActuals = permissionActualMapper.selectByExample(new PermissionActualExample());

        //查询所有的父功能
        FunctionExample example = new FunctionExample();
        example.createCriteria().andPidEqualTo(0);
        List<Function> parentfunction = functionMapper.selectByExample(example);
        List<ParentFunction> parentFunctions = new ArrayList<>();
        //将所有的子功能封装到父功能里面
        for (Function function : parentfunction) {
            List<SubFunction> subFunctions = new ArrayList<>();
            //把所有的权限封装到子功能里面
            for (Function subFunction : functions) {
                SubFunction subFunction1 = null;
                if (subFunction.getPid().equals(function.getId())){
                    List<SystemPermission> list = new ArrayList<>();
                    for (PermissionActual p : permissionActuals) {
                        if (p.getNid().equals(subFunction.getId())){
                            SystemPermission permission = new SystemPermission(p.getPermission(), p.getApi(), p.getLabel());
                            list.add(permission);
                        }
                    }
                    subFunction1 = new SubFunction(subFunction.getLabel(), subFunction.getLabel(), list);
                    subFunctions.add(subFunction1);
                }
            }
            ParentFunction parentFunction = new ParentFunction(function.getLabel(), function.getLabel(), subFunctions);
            parentFunctions.add(parentFunction);
        }
        PermissionsVo permissionsVo = new PermissionsVo(permissions, parentFunctions);
        return permissionsVo;
    }

    @Override
    public void setRolePermissions(RoleRermissionsBo permissions) {
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andRoleIdEqualTo(permissions.getRoleId());
        List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);
        List<String> list = permissions.getPermissions();
        Iterator<String> iterator = list.iterator();
        for (;iterator.hasNext();) {
            String s = iterator.next();
            for (Permission permission : permissionList) {
                if (s.equals(permission.getPermission())) {
                    iterator.remove();
                }
            }
        }
        Date date = new Date(System.currentTimeMillis());
        List<Permission> permissionArrayList = new ArrayList<>();
        for (String s : list) {
            Permission permission = new Permission(null, permissions.getRoleId(), s, date, date, false);
            permissionArrayList.add(permission);
        }
        permissionMapper.insertList(permissionArrayList);
    }
}
