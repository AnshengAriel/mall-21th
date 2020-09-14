package com.cskaoyan.mall.service.service_weixin_wangminyi;

import com.cskaoyan.mall.bean.bean_of_liuxufeng.Cart;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.CheckedGoods;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.GoodsForQueryCoupon;

import java.util.List;

public interface Wangminyi_GoodsService {
    String queryBriefById(Integer goodsId);

    String queryNameById(Integer goodsId);

    String queryUrlById(Integer goodsId);

    Integer queryRetailPriceById(Integer goodsId);

    Integer queryCategoryIdById(Integer goodsId);

    List<GoodsForQueryCoupon> cartsToGoodsForQueryCoupon(List<Cart> carts);
}
