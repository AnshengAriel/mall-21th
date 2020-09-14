package com.cskaoyan.mall.bean.bean_of_liuxufeng;

import com.cskaoyan.mall.utils.TypeConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponList2StringBo {
    private Integer id;
    private String name;
    private String desc;
    private String tag;
    private Integer total;
    private BigDecimal discount;
    private BigDecimal min;
    private Short limit;
    private Short type;
    private Short status;
    private Short goodsType;
    private List<Integer> goodsValue;
    private String code;
    private Short timeType;
    private Short days;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date addTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
    private Boolean deleted;

    public CouponList2StringBo(Coupon couponBo) {
        this.id = couponBo.getId();
        this.name = couponBo.getName();
        this.desc = couponBo.getDesc();
        this.tag = couponBo.getTag();
        this.total = couponBo.getTotal();
        this.discount = couponBo.getDiscount();
        this.min = couponBo.getMin();
        this.limit = couponBo.getLimit();
        this.type = couponBo.getType();
        this.status = couponBo.getStatus();
        this.goodsType = couponBo.getGoodsType();
        this.goodsValue = TypeConverter.parse(couponBo.getGoodsValue());
        this.code = couponBo.getCode();
        this.timeType = couponBo.getTimeType();
        this.days = couponBo.getDays();
        this.startTime = couponBo.getStartTime();
        this.endTime = couponBo.getEndTime();
        this.addTime = couponBo.getAddTime();
        this.updateTime = couponBo.getUpdateTime();
        this.deleted = couponBo.getDeleted();
    }
}
