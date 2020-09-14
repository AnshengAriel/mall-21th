package com.cskaoyan.mall.service.service_weixin_wangminyi;

import com.cskaoyan.mall.bean.BaseRespVo;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.Cart;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.CheckedOutBo;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.*;

import java.util.List;

public interface Wangminyi_CouponService {
    List<Wangminyi_Coupon> queryAllCoupons(Integer page, Integer size);

    List<CouponForListVo> couponToCouponForListVo(List<Wangminyi_Coupon> coupons);

    List<Wangminyi_Coupon_User> queryMyCoupons(Short status);

    List<Wangminyi_Coupon> queryCouponsByCouponUser(List<Wangminyi_Coupon_User> couponUsers);

    List<CouponForMylistVo> couponToCouponForMyListVo(List<Wangminyi_Coupon> coupons);

    Wangminyi_Coupon queryCouponById(Integer couponId);

    List<Wangminyi_Coupon_User> queryCouponUserByCouponId(Integer id);

    Wangminyi_Coupon_User couponToCouponUser(Wangminyi_Coupon coupon);

    BaseRespVo receiveAndExchangeCoupon(Integer couponId);

    BaseRespVo receiveAndExchangeCoupon(String code);

    Wangminyi_Coupon queryCouponByCode(String code);

    List<CouponForMylistVo> queryMyCouponsForSelectlist(List<GoodsForQueryCoupon> goodsForQueryCouponList);

    List<Wangminyi_Coupon> queryCouponByCategoryId(Integer categoryId);

}
