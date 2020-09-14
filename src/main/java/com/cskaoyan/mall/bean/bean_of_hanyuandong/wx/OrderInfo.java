package com.cskaoyan.mall.bean.bean_of_hanyuandong.wx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo {
    Integer id;
    String orderSn;
    String orderStatusText;
    String consignee;
    String mobile;
    String address;
    BigDecimal goodsPrice;
    BigDecimal freightPrice;
    BigDecimal couponPrice;
    BigDecimal actualPrice;
    Date addTime;
    HandleOptionVo handleOption;
}
