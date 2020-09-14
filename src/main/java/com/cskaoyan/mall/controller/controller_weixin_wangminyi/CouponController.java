package com.cskaoyan.mall.controller.controller_weixin_wangminyi;

import com.cskaoyan.mall.bean.BaseRespVo;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.Cart;
import com.cskaoyan.mall.bean.bean_of_wangminyi.BaseData;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.*;
import com.cskaoyan.mall.mapper.mapper_weixin_wangminyi.Wangminyi_CouponMapper;
import com.cskaoyan.mall.mapper.mapper_weixin_wangminyi.Wangminyi_Coupon_UserMapper;
import com.cskaoyan.mall.service.CartService;
import com.cskaoyan.mall.service.service_weixin_wangminyi.Wangminyi_CouponService;
import com.cskaoyan.mall.service.service_weixin_wangminyi.Wangminyi_GoodsService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("wx/coupon/")
public class CouponController {
    @Autowired
    Wangminyi_GoodsService goodsService;
    @Autowired
    CartService cartService;
    @Autowired
    Wangminyi_CouponService wangminyiCouponService;
    @Autowired
    Wangminyi_CouponMapper couponMapper;
    @Autowired
    Wangminyi_Coupon_UserMapper couponUserMapper;


    /*优惠券列表*/
    @RequestMapping("list")
    public BaseRespVo list(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        List<Wangminyi_Coupon> coupons = wangminyiCouponService.queryAllCoupons(page, size);
        List<CouponForListVo> couponVos = wangminyiCouponService.couponToCouponForListVo(coupons);
        DataForCouponList dataForCouponList = new DataForCouponList(couponVos, couponVos.size());
        return BaseRespVo.ok(dataForCouponList);
    }


    /*我的优惠券列表*/
    //status：0未使用   1已使用    2已过期
    @RequestMapping("mylist")
    public BaseRespVo mylist(@RequestParam("status") Short status,
                             @RequestParam("page") Integer page,
                             @RequestParam("size") Integer size) {
        PageHelper.startPage(page, size);
        List<Wangminyi_Coupon_User> couponUsers = wangminyiCouponService.queryMyCoupons(status);
        List<Wangminyi_Coupon> coupons = wangminyiCouponService.queryCouponsByCouponUser(couponUsers);
        List<CouponForMylistVo> couponForMylistVos = wangminyiCouponService.couponToCouponForMyListVo(coupons);
        if (couponForMylistVos == null) {
            return null;
        }
        DataForCouponList dataForCouponList = new DataForCouponList(couponForMylistVos, couponForMylistVos.size());
        return BaseRespVo.ok(dataForCouponList);
    }


    /*当前订单可用优惠券列表*/
    @RequestMapping("selectlist")
    public BaseRespVo selectlist(@RequestParam("cartId") Integer cartId,
                                 @RequestParam("grouponRulesId") Integer grouponRulesId) {
        List<Cart> carts = cartService.queryAvailableCouponsForCart(cartId);
        List<GoodsForQueryCoupon> goodsForQueryCouponList = goodsService.cartsToGoodsForQueryCoupon(carts);
        List<CouponForMylistVo> couponForMylistVo = wangminyiCouponService.queryMyCouponsForSelectlist(goodsForQueryCouponList);
        BaseRespVo baseRespVo = new BaseRespVo();
        return baseRespVo.ok(couponForMylistVo);
    }


    /*优惠券领取*/
    @RequestMapping("receive")
    public BaseRespVo receive(@RequestBody String couponId) {
        //这里的couponId用Integer接收不到，使用String接收的结果是{"couponId":8}
        String temp = couponId.substring(12, couponId.length() - 1);
        BaseRespVo baseRespVo = wangminyiCouponService.receiveAndExchangeCoupon(Integer.valueOf(temp));
        return baseRespVo;
    }

    /*优惠券兑换*/
    @RequestMapping("exchange")
    public BaseRespVo exchange(@RequestBody String code) {
        //根据输入的code查询是否有对应的优惠券
        //code接收的结果是{"code":"DC6FF8SE"}
        String temp = code.substring(9, code.length() - 2);
        Wangminyi_Coupon coupon = wangminyiCouponService.queryCouponByCode(temp);

        //输入错误的兑换码
        if (coupon == null) {
            return new BaseRespVo(null, "优惠券不正确", 742);
        }
        //考虑优惠券兑换与优惠券领取都是获得优惠券，采用方法重载
        return wangminyiCouponService.receiveAndExchangeCoupon(coupon.getId());
    }
}
