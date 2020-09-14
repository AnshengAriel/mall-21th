package com.cskaoyan.mall.bean.bean_of_wangminyi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GoodsProduct {
    private Integer id;

    private Integer goodsId;

    private String specifications;

    private BigDecimal price;

    private Integer number;

    private String url;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;

}