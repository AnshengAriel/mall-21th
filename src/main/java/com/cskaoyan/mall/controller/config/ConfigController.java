package com.cskaoyan.mall.controller.config;


import com.cskaoyan.mall.bean.BaseResoVo;
import com.cskaoyan.mall.bean.config.ExpressConfigBean;
import com.cskaoyan.mall.bean.config.MallConfigBean;
import com.cskaoyan.mall.bean.config.OrderConfigBean;
import com.cskaoyan.mall.bean.config.WxConfigBean;
import com.cskaoyan.mall.service.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/config")
public class ConfigController {

    @Autowired
    ConfigService configService;

    @GetMapping("mall")
    public BaseResoVo mall(){

        MallConfigBean mallConfigBean = configService.querytMallConfig();
        return BaseResoVo.ok(mallConfigBean);
    }

    @GetMapping("express")
    public BaseResoVo express(){

        ExpressConfigBean expressConfigBean = configService.selectExpressConfig();
        return BaseResoVo.ok(expressConfigBean);
    }

    @GetMapping("order")
    public BaseResoVo order(){

        OrderConfigBean orderConfigBean = configService.selectOrderConfig();
        return BaseResoVo.ok(orderConfigBean);
    }

    @GetMapping("wx")
    public BaseResoVo wx(){

        WxConfigBean wxConfigBean = configService.selectWxConfig();
        return BaseResoVo.ok(wxConfigBean);
    }

    @PostMapping("mall")
    public BaseResoVo mall(@RequestBody MallConfigBean mallConfigBean){
        configService.updateMallConfig(mallConfigBean);
        return BaseResoVo.ok();
    }

    @PostMapping("express")
    public BaseResoVo express(@RequestBody ExpressConfigBean expressConfigBean){
        configService.updateExpressConfig(expressConfigBean);
        return BaseResoVo.ok();
    }

    @PostMapping("order")
    public BaseResoVo order(@RequestBody OrderConfigBean orderConfigBean){
        configService.updateOrderConfig(orderConfigBean);
        return BaseResoVo.ok();
    }

    @PostMapping("wx")
    public BaseResoVo wx(@RequestBody WxConfigBean wxConfigBean){
        configService.updateWxConfig(wxConfigBean);
        return BaseResoVo.ok();
    }
}
