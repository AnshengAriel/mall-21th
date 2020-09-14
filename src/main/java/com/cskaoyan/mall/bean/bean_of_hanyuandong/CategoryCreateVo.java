package com.cskaoyan.mall.bean.bean_of_hanyuandong;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateVo {
    Integer id;
    String name;
    String keywords;
    String desc;
    Integer pid;
    String iconUrl;
    String picUrl;
    String level;
    Date addTime;
    Date updateTime;
    Boolean deleted;
}
