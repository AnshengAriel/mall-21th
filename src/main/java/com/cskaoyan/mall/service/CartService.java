package com.cskaoyan.mall.service;


import com.cskaoyan.mall.bean.bean_of_liuxufeng.*;

import java.util.List;
import java.util.Map;

import com.cskaoyan.mall.bean.bean_of_liuxufeng.CartListVo;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.CartProductBo;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.CheckedBo;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.ProductIds;

import java.util.List;


public interface CartService {
    CartListVo cartIndex();

    void updateCart(CartProductBo cartProductBo);

    CartListVo checkedCart(CheckedBo checkedBo);

    CartListVo deleteCartByProductIds(ProductIds productIds);

    Integer getGoodsCount();


    int addCountCart(AddCartBo addCartBo);

    Map checkedoutCart(CheckedOutBo checkedOutBo);

    List<Cart> queryAvailableCouponsForCart(Integer cartId);



}
