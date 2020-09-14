package com.cskaoyan.mall.bean.bean_of_hanyuandong.wx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsInfo {
    String goodsName;//goods表中
    Integer id;//goods表中
    Integer number;//order表中的goods_price / goods表中的retail_price
    String picUrl;//goods表中
}
