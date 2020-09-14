package com.cskaoyan.mall.bean.bean_of_liuxufeng;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckedOutBo {
    private Integer cartId;
    private Integer addressId;
    private Integer couponId;
    private Integer grouponRulesId;
}
