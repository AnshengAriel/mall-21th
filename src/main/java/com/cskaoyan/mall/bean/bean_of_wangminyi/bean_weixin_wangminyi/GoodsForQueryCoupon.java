package com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

//提供查询订单可用的优惠券所需的参数
public class GoodsForQueryCoupon {
    Integer goodsId;//商品id
    Integer goodsNumber;//商品数量
    //优惠券分三类，通用、用于指定类目、用于指定商品
    Integer goodsCategoryId;//商品类目id
    Integer price;//商品价格

}
