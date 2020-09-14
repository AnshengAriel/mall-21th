package com.cskaoyan.mall.bean.bean_of_hanyuandong;

import lombok.Data;

import java.util.List;

@Data
public class AdminDeleteBo {
    Integer id;
    String username;
    String avatar;
    List<Integer> roleIds;
}
