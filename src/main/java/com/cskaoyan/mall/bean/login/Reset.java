package com.cskaoyan.mall.bean.login;

import lombok.Data;

@Data
public class Reset {

    String code;
    String mobile;
    String password;
}
