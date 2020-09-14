package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.bean_of_liuxufeng.Coupon;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.CouponBo;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.CouponList2StringBo;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.CouponVo;

public interface CouponService {
    CouponVo queryCoupon(CouponBo coupon);

    int updateCouponById(Coupon coupon);

    int deleteCouponById(Coupon coupon);

    int createCoupon(CouponList2StringBo coupon);

    CouponList2StringBo selectCouponById(Integer id);
}
