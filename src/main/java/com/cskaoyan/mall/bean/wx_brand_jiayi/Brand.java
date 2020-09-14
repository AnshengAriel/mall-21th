package com.cskaoyan.mall.bean.wx_brand_jiayi;


import lombok.Data;

import java.util.Date;

@Data
public class Brand {

    Date addTime;
    Boolean deleted;
    String desc;
    Double floorPrice;
    Integer id;
    String name;
    String picUrl;
    Integer sortOrder;
    Date updateTime;
}
