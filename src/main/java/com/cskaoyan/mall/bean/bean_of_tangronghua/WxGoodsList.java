package com.cskaoyan.mall.bean.bean_of_tangronghua;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class WxGoodsList {
    Integer id;
    String brief;
    BigDecimal counterPrice;
    Boolean isHot;
    Boolean isNew;
    String name;
    String picUrl;
    BigDecimal retailPrice;
}
