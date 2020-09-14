package com.cskaoyan.mall.bean.bean_of_hanyuandong;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateBo {
    String name;
    String keywords;
    String level;
    Integer pid;
    String desc;
    String iconUrl;
    String picUrl;
}
