package com.cskaoyan.mall.bean.bean_of_wangyan;

import com.cskaoyan.mall.bean.bean_of_tangronghua.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailVo {
    Order order;
    List<OrderGoods> orderGoods;
    User user;
}
