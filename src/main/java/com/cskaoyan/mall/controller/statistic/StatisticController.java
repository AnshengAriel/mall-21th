package com.cskaoyan.mall.controller.statistic;


import com.cskaoyan.mall.bean.BaseResoVo;
import com.cskaoyan.mall.bean.statistic.*;
import com.cskaoyan.mall.service.statistic.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin/stat")
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @RequestMapping("user")
    public BaseResoVo usersStatistic(){

        List<UserBean> userBeans = statisticService.queryUserStatistic();
        UserStatisticBean userStatisticBean = new UserStatisticBean();
        userStatisticBean.setRows(userBeans);
        return BaseResoVo.ok(userStatisticBean);
       // return "{\"errno\":0,\"data\":{\"columns\":[\"add_time\",\"users\"],\"rows\":[{\"users\":3,\"add_time\":\"2020-04-27\"},{\"users\":3,\"add_time\":\"2020-04-28\"},{\"users\":3,\"add_time\":\"2020-04-29\"},{\"users\":-3,\"add_time\":\"2020-04-30\"}]},\"errmsg\":\"成功\"}";
    }

    @RequestMapping("order")
    public BaseResoVo ordersStatistic(){

        List<OrderBean> orderBeans = statisticService.queryOrderStatistic();
        OrderStatisticBean orderStatisticBean = new OrderStatisticBean();
        orderStatisticBean.setRows(orderBeans);
        return BaseResoVo.ok(orderStatisticBean);
        //return "{\"errno\":0,\"data\":{\"columns\":[\"day\",\"orders\",\"customers\",\"amount\",\"pcr\"],\"rows\":[{\"amount\":4989.00,\"orders\":400,\"customers\":99,\"day\":\"2020-04-27\",\"pcr\":1663.00}]},\"errmsg\":\"成功\"}";
    }

    @RequestMapping("goods")
    public BaseResoVo goodsStatistic(){

        List<GoodBean> goodBeans = statisticService.queryGoodStatistic();
        GoodStatisticBean goodStatisticBean = new GoodStatisticBean();
        goodStatisticBean.setRows(goodBeans);
        return BaseResoVo.ok(goodStatisticBean);

    }
}
