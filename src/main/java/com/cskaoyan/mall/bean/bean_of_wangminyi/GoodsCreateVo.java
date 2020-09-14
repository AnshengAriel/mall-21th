package com.cskaoyan.mall.bean.bean_of_wangminyi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsCreateVo {
    private Goods goods;

    private List<GoodsSpecification> specifications;

    private List<GoodsProduct> products;

    private List<GoodsAttribute> attributes;
}
