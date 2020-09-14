package com.cskaoyan.mall.service.config;

import com.cskaoyan.mall.bean.config.ExpressConfigBean;
import com.cskaoyan.mall.bean.config.MallConfigBean;
import com.cskaoyan.mall.bean.config.OrderConfigBean;
import com.cskaoyan.mall.bean.config.WxConfigBean;


public interface ConfigService {

    MallConfigBean querytMallConfig();
    ExpressConfigBean selectExpressConfig();
    OrderConfigBean selectOrderConfig();
    WxConfigBean selectWxConfig();

    int updateMallConfig(MallConfigBean mallConfigBean);
    int updateExpressConfig(ExpressConfigBean expressConfigBean);
    int updateOrderConfig(OrderConfigBean orderConfigBean);
    int updateWxConfig(WxConfigBean wxConfigBean);
}
