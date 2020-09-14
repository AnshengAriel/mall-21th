package com.cskaoyan.mall.mapper.config;

import com.cskaoyan.mall.bean.config.ExpressConfigBean;
import com.cskaoyan.mall.bean.config.MallConfigBean;
import com.cskaoyan.mall.bean.config.OrderConfigBean;
import com.cskaoyan.mall.bean.config.WxConfigBean;
import org.apache.ibatis.annotations.Param;

public interface ConfigMapper {

    MallConfigBean selectMallConfig();
    ExpressConfigBean selectExpressConfig();
    OrderConfigBean selectOrderConfig();
    WxConfigBean selectWxConfig();

    int updateMallConfig(@Param("mallConfigBean") MallConfigBean mallConfigBean);
    int updateExpressConfig(@Param("expressConfigBean") ExpressConfigBean expressConfigBean);
    int updateOrderConfig(@Param("orderConfigBean") OrderConfigBean orderConfigBean);
    int updateWxConfig(@Param("wxConfigBean") WxConfigBean wxConfigBean);
}
