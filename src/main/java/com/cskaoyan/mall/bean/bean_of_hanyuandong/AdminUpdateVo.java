package com.cskaoyan.mall.bean.bean_of_hanyuandong;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUpdateVo {
    private Integer id;

    private String username;

    private String password;

    private String avatar;

    private Date updateTime;

    private List<Integer> roleIds;
}
