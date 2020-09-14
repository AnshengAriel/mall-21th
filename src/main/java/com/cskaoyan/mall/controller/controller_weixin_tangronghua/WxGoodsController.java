package com.cskaoyan.mall.controller.controller_weixin_tangronghua;

import com.cskaoyan.mall.bean.BaseRespVo;

import com.cskaoyan.mall.service.WxGoodsService;
import com.cskaoyan.mall.service.WxHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RequestMapping("wx/goods")
@RestController
public class WxGoodsController {

    @Autowired
    WxGoodsService wxGoodsService;

    @RequestMapping("category")
    public BaseRespVo goodsCategory(Integer id){
        Map<String,Object> map = wxGoodsService.goodsCategory(id);
        return BaseRespVo.ok(map);
    }

    @GetMapping("detail")
    public BaseRespVo goodsDetail(@RequestParam("id")Integer id){
        Map<String,Object> map = wxGoodsService.goodsDetail(id);
        return BaseRespVo.ok(map);
    }

    @GetMapping("related")
    public BaseRespVo goodsRelated(@RequestParam("id") Integer id){
        Map<String,Object> map = wxGoodsService.goodsRelated(id);
        return BaseRespVo.ok(map);
    }
    @GetMapping("list")
    public BaseRespVo goodsList(Integer categoryId,Integer page,Integer size){
        Map<String,Object> map = wxGoodsService.goodsList(categoryId,page,size);
        return BaseRespVo.ok(map);
    }
}
