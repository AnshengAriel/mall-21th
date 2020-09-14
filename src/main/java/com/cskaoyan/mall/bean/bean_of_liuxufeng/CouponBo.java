package com.cskaoyan.mall.bean.bean_of_liuxufeng;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponBo {
    private Integer page;
    private Integer limit;
    private String sort;
    private String order;
    private Short type;
    private Short status;
    private String name;
    private Integer couponId;
}
