package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.bean_of_hanyuandong.Admin;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.AdminCreateVo;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.AdminExample;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.AdminVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AdminMapper {
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int logicDeleteByPrimaryKey(@Param("id") Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    //List<AdminVo> selectAdmins(String sort, String order);

    List<AdminVo> selectAdmins(String sort, String order,String username);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    int updateById(@Param("id") Integer id, @Param("username") String username, @Param("avatar")String avatar, @Param("roleIds")String roleIds, @Param("password") String password,@Param("updateTime") Date updateTime);
}