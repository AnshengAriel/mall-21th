package com.cskaoyan.mall.bean.bean_of_hanyuandong;

import lombok.Data;

import java.util.List;

@Data
public class RoleListVo {
    Long total;
    List<Role> items;
}
