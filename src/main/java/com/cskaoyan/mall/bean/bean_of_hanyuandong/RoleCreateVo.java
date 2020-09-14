package com.cskaoyan.mall.bean.bean_of_hanyuandong;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleCreateVo {
    private Integer id;

    private String name;

    private String desc;

    private Date addTime;

    private Date updateTime;
}
