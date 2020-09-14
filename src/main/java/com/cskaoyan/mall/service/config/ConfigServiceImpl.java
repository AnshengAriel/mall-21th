package com.cskaoyan.mall.service.config;

import com.cskaoyan.mall.bean.config.ExpressConfigBean;
import com.cskaoyan.mall.bean.config.MallConfigBean;
import com.cskaoyan.mall.bean.config.OrderConfigBean;
import com.cskaoyan.mall.bean.config.WxConfigBean;
import com.cskaoyan.mall.mapper.config.ConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    ConfigMapper configMapper;

    @Override
    public MallConfigBean querytMallConfig() {
        return configMapper.selectMallConfig();
    }

    @Override
    public ExpressConfigBean selectExpressConfig() {
        return configMapper.selectExpressConfig();
    }

    @Override
    public OrderConfigBean selectOrderConfig() {
        return configMapper.selectOrderConfig();
    }

    @Override
    public WxConfigBean selectWxConfig() {
        return configMapper.selectWxConfig();
    }

    @Override
    public int updateMallConfig(MallConfigBean mallConfigBean) {
        return configMapper.updateMallConfig(mallConfigBean);
    }

    @Override
    public int updateExpressConfig(ExpressConfigBean expressConfigBean) {
        return configMapper.updateExpressConfig(expressConfigBean);
    }

    @Override
    public int updateOrderConfig(OrderConfigBean orderConfigBean) {
        return configMapper.updateOrderConfig(orderConfigBean);
    }

    @Override
    public int updateWxConfig(WxConfigBean wxConfigBean) {
        return configMapper.updateWxConfig(wxConfigBean);
    }
}
