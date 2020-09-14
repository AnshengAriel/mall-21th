package com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FootprintForList {
    private Date addTime;
    private String brief;
    private Integer goodsId;
    private Integer id;
    private String name;
    private String picUrl;
    private Integer retailPrice;
}
