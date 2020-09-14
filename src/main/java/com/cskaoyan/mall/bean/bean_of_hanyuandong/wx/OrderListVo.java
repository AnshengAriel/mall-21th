package com.cskaoyan.mall.bean.bean_of_hanyuandong.wx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderListVo {
    Long count;//总订单数
    List<OrderData> data;
    Long totalPages;//总页数 = 订单数/每页条目数（在请求中）
}
