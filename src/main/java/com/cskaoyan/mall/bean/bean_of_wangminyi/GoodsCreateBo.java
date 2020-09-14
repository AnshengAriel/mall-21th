package com.cskaoyan.mall.bean.bean_of_wangminyi;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsCreateBo {
    private GoodsBo goods;

    private List<GoodsSpecificationBo> specifications;

    private List<GoodsProductBo> products;

    private List<GoodsAttributeBo> attributes;
}
