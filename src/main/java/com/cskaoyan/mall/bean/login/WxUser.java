package com.cskaoyan.mall.bean.login;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class WxUser {

    Serializable token;
    Date tokenExpire;
    UserInfo userInfo;
}
