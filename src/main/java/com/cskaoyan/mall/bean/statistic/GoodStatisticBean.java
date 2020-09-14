package com.cskaoyan.mall.bean.statistic;

import lombok.Data;

import java.util.List;

@Data
public class GoodStatisticBean {

    String[] columns;
    List<GoodBean> rows;

    public GoodStatisticBean() {
        columns=new String[]{"day","amount","orders","products"};
    }
}
