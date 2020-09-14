package com.cskaoyan.mall.bean.bean_of_liuxufeng;

import com.cskaoyan.mall.bean.bean_of_wangminyi.Goods;
import com.cskaoyan.mall.bean.bean_of_wangminyi.GoodsProduct;
import com.cskaoyan.mall.utils.List_string2StringConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cart {

    private Integer id;

    private Integer userId;

    private Integer goodsId;

    private String goodsSn;

    private String goodsName;

    private Integer productId;

    private BigDecimal price;

    private Short number;

    private String specifications;

    private Boolean checked;

    private String picUrl;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;

    public Cart(Goods goods, GoodsProduct goodsProduct, Short number) {
        this.id = id;
        this.userId = 1;  //此处还没有整合userId，所以先置为1
        this.goodsId = goods.getId();
        this.goodsSn = goods.getGoodsSn();
        this.goodsName = goods.getName();
        this.productId = goodsProduct.getId();
        this.price = goodsProduct.getPrice();
        this.number = number;
        this.specifications = goodsProduct.getSpecifications();
        this.checked = true;
        this.picUrl = goodsProduct.getUrl();
        this.addTime = new Date();
        this.updateTime = new Date();
        this.deleted = false;
    }


    public CartListBean change2CartListBean(CartListBean cartListBean){
        cartListBean.setId(id);
        cartListBean.setUserId(userId);
        cartListBean.setGoodsId(goodsId);
        cartListBean.setGoodsSn(goodsSn);
        cartListBean.setGoodsName(goodsName);
        cartListBean.setProductId(productId);
        cartListBean.setPrice(price);
        cartListBean.setNumber(number);
        cartListBean.setChecked(checked);
        cartListBean.setPicUrl(picUrl);
        cartListBean.setAddTime(addTime);
        cartListBean.setUpdateTime(updateTime);
        cartListBean.setDeleted(deleted);
        cartListBean.setSpecifications(List_string2StringConverter.parse(this.specifications));//List2String
        return cartListBean;
    }
}