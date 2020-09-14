package com.cskaoyan.mall.service.statistic;


import com.cskaoyan.mall.bean.statistic.GoodBean;
import com.cskaoyan.mall.bean.statistic.OrderBean;
import com.cskaoyan.mall.bean.statistic.UserBean;

import java.util.List;

public interface StatisticService {


    List<UserBean> queryUserStatistic();
    List<OrderBean> queryOrderStatistic();
    List<GoodBean> queryGoodStatistic();
}
