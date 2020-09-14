package com.cskaoyan.mall.bean.bean_of_hanyuandong.wx;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HandleOptionVo {
    boolean cancel;//取消订单按钮，未付款的，101
    boolean comment;//评论按钮，已收货的，401,402
    boolean confirm;//确认收货订单按钮，已发货的，301
    boolean delete;//删除订单按钮，已收货的，401,402,102,103
    boolean pay;//支付按钮，未付款的，101
    boolean rebuy;//再次购买按钮，已收货的，401,402
    boolean refund;//退款按钮，已付款的，201
}
