package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.bean_of_liuxufeng.*;
import com.cskaoyan.mall.mapper.CouponMapper;
import com.cskaoyan.mall.service.CouponService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    CouponMapper couponMapper;

    @Override
    public CouponVo queryCoupon(CouponBo coupon) {
        CouponExample couponExample = new CouponExample();
        couponExample.setOrderByClause(coupon.getSort()+" "+coupon.getOrder());
        /*CouponStatusEnum[] values = CouponStatusEnum.values(); //把所有的enum都获取到
        枚举暂时放一边
        couponExample.createCriteria().andNameLike("%"+coupon.getName()+"%").andStatusEqualTo();*/
        PageHelper.startPage(coupon.getPage(),coupon.getLimit());
        List<Coupon> coupons = couponMapper.selectByExample(couponExample);
        PageInfo<Coupon> PageInfo = new PageInfo<>(coupons);
        long total = PageInfo.getTotal();
        CouponVo couponVo = new CouponVo(total, coupons);
        return couponVo;
    }

    @Override
    public int updateCouponById(Coupon coupon) {
        CouponExample couponExample = new CouponExample();
        couponExample.createCriteria().andIdEqualTo(coupon.getId());
        int i = couponMapper.updateByExample(coupon, couponExample);
        return i;
    }

    @Override
    public int deleteCouponById(Coupon coupon) {
        CouponExample couponExample = new CouponExample();
        couponExample.createCriteria().andIdEqualTo(coupon.getId());
        int i = couponMapper.deleteByExample(couponExample);
        return i;
    }

    /** 类型转换接收出错 ,暂时放一边
     * Resolved [org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Cannot deserialize instance of `java.util.ArrayList<java.lang.Integer>` out of VALUE_NUMBER_INT token; nested exception is com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot deserialize instance of `java.util.ArrayList<java.lang.Integer>` out of VALUE_NUMBER_INT token
     *  at [Source: (PushbackInputStream); line: 1, column: 137] (through reference chain: com.cskaoyan.mall.bean.bean_of_liuxufeng.CouponList2StringBo["goodsType"])]
     * @param coupon
     * @return
     */
    @Override
    public int createCoupon(CouponList2StringBo coupon) {
        Coupon coupon1 = new Coupon(coupon);
        int i = couponMapper.insertSelective(coupon1);
        return coupon1.getId();
    }

    @Override
    public CouponList2StringBo selectCouponById(Integer id) {
        Coupon coupon = couponMapper.selectByPrimaryKey(id);
        CouponList2StringBo couponList2StringBo = new CouponList2StringBo(coupon);
        return couponList2StringBo;
    }
}
