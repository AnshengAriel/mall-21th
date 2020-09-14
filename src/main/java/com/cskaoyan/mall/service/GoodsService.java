package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.bean_of_wangminyi.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GoodsService {
    BaseData queryGoods(Integer page, Integer limit, String sort, String order);

    List<CategoryVoList> queryCat();

    int deleteGoods(Goods goods);

    int insertGoods(Goods goods);

    int getInsertId();

    int insertGoodsSpecifications(List<GoodsSpecification> specifications, int goodsId);

    int insertGoodsProducts(List<GoodsProduct> products, int goodsId);

    int insertAttributes(List<GoodsAttribute> attributes, int goodsId);

    List<CategoryChildrenVoList> queryBrands();

    Goods queryGoodsById(Integer goodsSn);

    Integer queryCategoryIdByGoodsId(Integer id);


    Integer queryPidByCategoryId(Integer categoryId);

}
