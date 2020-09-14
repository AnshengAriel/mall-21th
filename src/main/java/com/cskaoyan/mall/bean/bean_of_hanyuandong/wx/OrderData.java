package com.cskaoyan.mall.bean.bean_of_hanyuandong.wx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderData {
    Integer actualPrice;//实付钱数，order表中
    List<GoodsInfo> goodsList;//在order_goods表中
    HandleOptionVo handleOption;//用该是根据订单状态为其赋值
    Integer id;//订单id
    boolean isGroupin;//
    String orderSn;//订单编号，order表中
    String orderStatusText;//订单状态，order表中的订单状态码对应得出
}
