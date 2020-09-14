package com.cskaoyan.mall.bean.bean_of_tangronghua;

import com.cskaoyan.mall.bean.bean_of_wangminyi.GoodsProduct;
import com.cskaoyan.mall.utils.List_string2StringConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsProductVo {
    private Integer id;

    private Integer goodsId;

    private List specifications;

    private BigDecimal price;

    private Integer number;

    private String url;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;

    public GoodsProductVo(GoodsProduct goodsProducts) {
        this.id = goodsProducts.getId();
        this.goodsId = goodsProducts.getGoodsId();
        String specifications = goodsProducts.getSpecifications();
        this.specifications = List_string2StringConverter.parse(specifications);
        this.price = goodsProducts.getPrice();
        this.number = goodsProducts.getNumber();
        this.url = goodsProducts.getUrl();
        this.addTime = goodsProducts.getAddTime();
        this.updateTime = goodsProducts.getUpdateTime();
        this.deleted = goodsProducts.getDeleted();
    }
}
