package com.cskaoyan.mall.service.login;

import com.cskaoyan.mall.bean.login.Order;
import com.cskaoyan.mall.bean.login.UserInfo;
import com.cskaoyan.mall.mapper.login.UserMapper9;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WxLoginServiceImpl implements WxLoginService{

    @Autowired
    UserMapper9 userMapper9;

    @Override
    public UserInfo querytInfoByName(String username) {
        return userMapper9.selectInfoByName(username);
    }

    @Override
    public Order queryOrderCount(String username) {
        return userMapper9.selectOrderCount(username);
    }

    @Override
    public Integer selectCount(String username) {
        return  userMapper9.selectCount(username);
    }

    @Override
    public Integer insertUser(String username, String password, String mobile) {
        return userMapper9.insertUser(username, password, mobile);
    }

    @Override
    public Integer resetPassword(String mobile, String password) {
        return userMapper9.resetPassword(mobile, password);
    }
}
