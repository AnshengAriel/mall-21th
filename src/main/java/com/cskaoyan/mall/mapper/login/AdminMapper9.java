package com.cskaoyan.mall.mapper.login;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminMapper9 {

    @Select("select password from cskaoyanmall_admin where username =#{username}")
    List<String> selectPasswordByName(@Param("username") String username);

    @Select("SELECT permission FROM cskaoyanmall_permission WHERE  role_id=( select role_ids from cskaoyanmall_admin where username =#{username})")
    List<String> selectPermissionByUsername(@Param("username") String username);
}
