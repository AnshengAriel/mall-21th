package com.cskaoyan.mall.bean.statistic;

import lombok.Data;

import java.util.List;

@Data
public class OrderStatisticBean {

    String[] columns;
    List<OrderBean> rows;

    public OrderStatisticBean() {
        columns=new String[]{"day","amount","orders","customers","pcr"};
    }
}
