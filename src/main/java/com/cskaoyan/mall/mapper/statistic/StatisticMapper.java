package com.cskaoyan.mall.mapper.statistic;

import com.cskaoyan.mall.bean.statistic.GoodBean;
import com.cskaoyan.mall.bean.statistic.OrderBean;
import com.cskaoyan.mall.bean.statistic.UserBean;

import java.util.List;

public interface StatisticMapper {

    List<UserBean> selectUserStatistic();
    List<OrderBean> selectOrderStatistic();
    List<GoodBean> selectGoodStatistic();
}
