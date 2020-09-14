package com.cskaoyan.mall.mapper.login;

import com.cskaoyan.mall.bean.login.Order;
import com.cskaoyan.mall.bean.login.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper9 {

    List<String> selectPasswordByName(@Param("username") String username);

    UserInfo selectInfoByName(@Param("username") String username);

    Order selectOrderCount(@Param("username") String username);

    Integer selectCount(@Param("username") String username);

    Integer insertUser(@Param("username") String username, @Param("password") String password, @Param("mobile") String mobile);

    Integer resetPassword(@Param("mobile") String mobile, @Param("password") String password);
}
