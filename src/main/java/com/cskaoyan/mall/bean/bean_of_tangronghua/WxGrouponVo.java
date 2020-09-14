package com.cskaoyan.mall.bean.bean_of_tangronghua;

import com.cskaoyan.mall.bean.bean_of_wangminyi.Goods;
import lombok.Data;

import java.util.List;

@Data
public class WxGrouponVo {
    List<Goods> goods;
    long groupon_member;
    long groupon_price;
}
