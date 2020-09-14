package com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponForListVo {
    private Short days;
    private String desc;
    private BigDecimal discount;
    private Integer id;
    private BigDecimal min;
    private String name;
    private String tag;

}
