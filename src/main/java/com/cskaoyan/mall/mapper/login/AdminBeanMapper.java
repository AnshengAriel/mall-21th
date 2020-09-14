package com.cskaoyan.mall.mapper.login;

import com.cskaoyan.mall.bean.login.AdminBean;
import com.cskaoyan.mall.bean.login.AdminBeanExample;
import com.cskaoyan.mall.bean.login.InfoBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminBeanMapper {
    long countByExample(AdminBeanExample example);

    int deleteByExample(AdminBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminBean record);

    int insertSelective(AdminBean record);

    List<AdminBean> selectByExample(AdminBeanExample example);

    AdminBean selectByPrimaryKey(Integer id);

    InfoBean selectByName(@Param("username") String username);

    int updateByExampleSelective(@Param("record") AdminBean record, @Param("example") AdminBeanExample example);

    int updateByExample(@Param("record") AdminBean record, @Param("example") AdminBeanExample example);

    int updateByPrimaryKeySelective(AdminBean record);

    int updateByPrimaryKey(AdminBean record);

    String queryRolenameById(@Param("role_id") Integer role_id);

    List<String> queryPermissionById(@Param("role_id") Integer role_id);
}