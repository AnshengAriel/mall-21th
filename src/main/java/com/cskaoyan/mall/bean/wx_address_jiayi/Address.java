package com.cskaoyan.mall.bean.wx_address_jiayi;

import lombok.Data;

import java.util.List;

@Data
public class Address {

    Integer id;
    String name;
    String mobile;
    String detailedAddress;
    String is_default;
    Boolean isDefault;
}
