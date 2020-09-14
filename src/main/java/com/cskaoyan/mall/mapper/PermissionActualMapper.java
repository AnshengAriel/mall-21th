package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.bean_of_hanyuandong.SystemPermission;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.wx.PermissionActual;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.wx.PermissionActualExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionActualMapper {
    long countByExample(PermissionActualExample example);

    int deleteByExample(PermissionActualExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PermissionActual record);

    int insertSelective(PermissionActual record);

    List<PermissionActual> selectByExample(PermissionActualExample example);

    PermissionActual selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PermissionActual record, @Param("example") PermissionActualExample example);

    int updateByExample(@Param("record") PermissionActual record, @Param("example") PermissionActualExample example);

    int updateByPrimaryKeySelective(PermissionActual record);

    int updateByPrimaryKey(PermissionActual record);

    List<SystemPermission> selectPermission();
}