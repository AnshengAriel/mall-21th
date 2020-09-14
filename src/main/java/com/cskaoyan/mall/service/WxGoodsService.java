package com.cskaoyan.mall.service;

import java.util.Map;

public interface WxGoodsService {
    Integer getUserId();

    Map<String, Object> goodsDetail(Integer id);

    Map<String,Object> goodsCategory(Integer id);

    Map<String,Object> goodsRelated(Integer id);

    Map<String,Object> goodsList(Integer categoryId, Integer page, Integer size);
}
