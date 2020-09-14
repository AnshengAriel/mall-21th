package com.cskaoyan.mall.bean.bean_of_liuxufeng;

import com.cskaoyan.mall.utils.List_string2StringConverter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CartListBean {
    /**
     * id : 109
     * userId : 1
     * goodsId : 1113010
     * goodsSn : 1113010
     * goodsName : 男式丝滑莫代尔平角内裤
     * productId : 156
     * price : 59
     * number : 1
     * specifications : ["标准"]
     * checked : true
     * picUrl : http://yanxuan.nosdn.127.net/2d0920b51331bb1636330ad8e07d1b97.png
     * addTime : 2020-06-30 19:58:50
     * updateTime : 2020-06-30 19:58:50
     * deleted : false
     */

    private int id;
    private int userId;
    private int goodsId;
    private String goodsSn;
    private String goodsName;
    private int productId;
    private BigDecimal price;
    private Short number;
    private boolean checked;
    private String picUrl;
    private Date addTime;
    private Date updateTime;
    private boolean deleted;
    private List<String> specifications;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Short getNumber() {
        return number;
    }

    public void setNumber(Short number) {
        this.number = number;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<String> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<String> specifications) {
        this.specifications = specifications;
    }

    public Cart change2Cart(Cart cart){
        cart.setId(id);
        cart.setUserId(userId);
        cart.setGoodsId(goodsId);
        cart.setGoodsSn(goodsSn);
        cart.setGoodsName(goodsName);
        cart.setProductId(productId);
        cart.setPrice(price);
        cart.setNumber(number);
        cart.setChecked(checked);
        cart.setPicUrl(picUrl);
        cart.setAddTime(addTime);
        cart.setUpdateTime(updateTime);
        cart.setDeleted(deleted);
        cart.setSpecifications(List_string2StringConverter.parse(this.specifications));//List2String
        return cart;
    }
}