package com.cskaoyan.mall.bean.login;


import lombok.Data;

import java.util.List;

@Data
public class InfoBean {

    String avatar;
    String name;
    List<Integer> role_ids;
    List<String> roles;
    List<String> perms;

}
