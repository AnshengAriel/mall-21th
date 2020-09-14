package com.cskaoyan.mall.bean.statistic;

import lombok.Data;

import java.util.List;

@Data
public class UserStatisticBean {

    String[] columns;
    List<UserBean> rows;

    public void setColumns(String[] columns) {
        columns=new String[]{"day","users"};
    }

    public UserStatisticBean() {
        columns=new String[]{"day","users"};
    }
}
