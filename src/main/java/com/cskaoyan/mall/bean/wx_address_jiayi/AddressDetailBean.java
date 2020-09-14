package com.cskaoyan.mall.bean.wx_address_jiayi;


import lombok.Data;

@Data
public class AddressDetailBean {

    Integer id;
    String name;
    String mobile;
    String address;
    String is_default;
    Boolean isDefault;

    Integer provinceId;
    Integer cityId;
    Integer areaId;

    String provinceName;
    String cityName;
    String areaName;
}
