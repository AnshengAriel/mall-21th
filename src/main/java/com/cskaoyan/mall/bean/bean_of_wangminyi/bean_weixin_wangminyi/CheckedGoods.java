package com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

//根据weixin/cart/checkout的response中的checkedGoodsList建立
public class CheckedGoods {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date addTime;

    Boolean checked;

    Boolean deleted;

    Integer goodsId;

    String goodsName;

    String goodsSn;

    Integer id;

    Integer number;

    String picUrl;

    Integer price;

    Integer productId;

    List<String> specifications;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date updateTime;

    Integer userId;

}
