package com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponForMylistVo {
    private String desc;
    private BigDecimal discount;
    private Date endTime;
    private Integer id;
    private BigDecimal min;
    private String name;
    private Date startTime;
    private String tag;

}
