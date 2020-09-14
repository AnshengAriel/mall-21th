package com.cskaoyan.mall.service.login;


import com.cskaoyan.mall.bean.login.Order;
import com.cskaoyan.mall.bean.login.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface WxLoginService {

    UserInfo querytInfoByName(String username);

    Order queryOrderCount(String username);

    Integer selectCount(String username);

    Integer insertUser(String username,String password,String mobile);

    Integer resetPassword(String mobile,String password);
}
