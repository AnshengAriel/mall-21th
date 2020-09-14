package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.bean_of_hanyuandong.wx.Permission;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.wx.PermissionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {
    long countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<String> selectPermissionsByRoleId(@Param("roleId") Integer roleId);

    void insertList(@Param("list") List<Permission> list);
}