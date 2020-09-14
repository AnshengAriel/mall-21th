package com.cskaoyan.mall.controller.controller_weixin_tangronghua;

import com.cskaoyan.mall.bean.BaseRespVo;
import com.cskaoyan.mall.service.WxHomeService;

import com.cskaoyan.mall.service.WxHomeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("wx")
public class WxHomeController {

    @Autowired
    WxHomeService wxHomeService;

    @RequestMapping("home/index")
    public BaseRespVo homeIndex(){
        Map<String,Object> map = wxHomeService.homeIndex();
        return BaseRespVo.ok(map);
    }

    @RequestMapping("goods/count")
    public BaseRespVo goodsCount(){
        Map<String,Object> map = wxHomeService.goodsCount();
        return BaseRespVo.ok(map);
    }
}
