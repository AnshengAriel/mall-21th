package com.cskaoyan.mall.service.service_weixin_wangminyi.impl;

import com.cskaoyan.mall.bean.bean_of_liuxufeng.Cart;
import com.cskaoyan.mall.bean.bean_of_wangminyi.GoodsExample;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.CheckedGoods;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.GoodsForQueryCoupon;
import com.cskaoyan.mall.mapper.mapper_weixin_wangminyi.Wangminyi_GoodsMapper;
import com.cskaoyan.mall.service.service_weixin_wangminyi.Wangminyi_GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Wangminyi_GoodsServiceImpl implements Wangminyi_GoodsService {
    @Autowired
    Wangminyi_GoodsMapper goodsMapper;

    //根据商品的id查询商品简介
    @Override
    public String queryBriefById(Integer goodsId) {
        return goodsMapper.selectByPrimaryKey(goodsId).getBrief();
    }

    @Override
    public String queryNameById(Integer goodsId) {
        return goodsMapper.selectByPrimaryKey(goodsId).getName();
    }

    @Override
    public String queryUrlById(Integer goodsId) {
        return goodsMapper.selectByPrimaryKey(goodsId).getPicUrl();
    }

    @Override
    public Integer queryRetailPriceById(Integer goodsId) {
        return goodsMapper.selectByPrimaryKey(goodsId).getRetailPrice().intValue();
    }

    @Override
    public Integer queryCategoryIdById(Integer goodsId) {
        return goodsMapper.selectByPrimaryKey(goodsId).getCategoryId();
    }



    @Override
    public List<GoodsForQueryCoupon> cartsToGoodsForQueryCoupon(List<Cart> carts) {
        List<GoodsForQueryCoupon> goodsForQueryCouponList = new ArrayList<>();
        for (Cart cart : carts) {
            GoodsForQueryCoupon goodsForQueryCoupon = new GoodsForQueryCoupon();
            goodsForQueryCoupon.setGoodsId(cart.getGoodsId());
            goodsForQueryCoupon.setGoodsNumber(cart.getNumber()+0);
            goodsForQueryCoupon.setGoodsCategoryId( this.queryCategoryIdById(cart.getGoodsId()));
            goodsForQueryCoupon.setPrice(cart.getPrice().intValue());
            goodsForQueryCouponList.add(goodsForQueryCoupon);
        }
        return goodsForQueryCouponList;
    }
}
