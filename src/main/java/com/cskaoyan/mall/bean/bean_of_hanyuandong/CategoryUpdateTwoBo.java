package com.cskaoyan.mall.bean.bean_of_hanyuandong;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateTwoBo {
    Integer id;
    String name;
    String keywords;
    String desc;
    String iconUrl;
    String picUrl;
    String level;
    Integer pid;
}
