package com.cskaoyan.mall.bean.bean_of_tangronghua;

import com.cskaoyan.mall.bean.bean_of_wangminyi.Goods;
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
public class GoodsInfo {
    private Integer id;
    private String goodsSn;
    private String name;
    private Integer categoryId;
    private Integer brandId;
    private List gallery;
    private String keywords;
    private String brief;
    private Boolean isOnSale;
    private Short sortOrder;
    private String picUrl;
    private String shareUrl;
    private Boolean isNew;
    private Boolean isHot;
    private String unit;
    private BigDecimal counterPrice;
    private BigDecimal retailPrice;
    private Date addTime;
    private Date updateTime;
    private Boolean deleted;

    public void Goods2Info(Goods goods) {
        this.id = goods.getId();
        this.goodsSn = goods.getGoodsSn();
        this.name = goods.getName();
        this.categoryId = goods.getCategoryId();
        this.brandId = goods.getBrandId();
        this.gallery = List_string2StringConverter.parse(goods.getGallery());
        this.keywords = goods.getKeywords();
        this.brief = goods.getBrief();
        this.isOnSale = goods.getIsOnSale();
        this.sortOrder = goods.getSortOrder();
        this.picUrl = goods.getPicUrl();
        this.shareUrl = goods.getShareUrl();
        this.isNew = goods.getIsNew();
        this.isHot = goods.getIsHot();
        this.unit = goods.getUnit();
        this.counterPrice = goods.getCounterPrice();
        this.retailPrice = goods.getRetailPrice();
        this.addTime = goods.getAddTime();
        this.updateTime = goods.getUpdateTime();
        this.deleted = goods.getDeleted();
    }
}
