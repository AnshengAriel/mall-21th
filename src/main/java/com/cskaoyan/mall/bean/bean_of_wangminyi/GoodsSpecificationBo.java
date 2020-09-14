package com.cskaoyan.mall.bean.bean_of_wangminyi;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsSpecificationBo {
    private Integer id;

    private Integer goodsId;

    private String specification;

    private String value;

    private String picUrl;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Boolean deleted;
}
