package com.cskaoyan.mall.service.service_weixin_wangminyi.impl;

import com.cskaoyan.mall.bean.BaseRespVo;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.Cart;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.CartExample;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.CheckedOutBo;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.CouponExample;
import com.cskaoyan.mall.bean.bean_of_wangminyi.User;
import com.cskaoyan.mall.bean.bean_of_wangminyi.UserExample;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.*;
import com.cskaoyan.mall.mapper.CartMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.mapper.mapper_weixin_wangminyi.Wangminyi_CouponMapper;
import com.cskaoyan.mall.mapper.mapper_weixin_wangminyi.Wangminyi_Coupon_UserMapper;
import com.cskaoyan.mall.service.CouponService;
import com.cskaoyan.mall.service.WxGoodsService;
import com.cskaoyan.mall.service.service_weixin_wangminyi.Wangminyi_CouponService;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class Wangminyi_CouponServiceImpl implements Wangminyi_CouponService {
    @Autowired
    Wangminyi_CouponMapper couponMapper;
    @Autowired
    Wangminyi_Coupon_UserMapper couponUserMapper;
    @Autowired
    CartMapper cartMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    WxGoodsService goodsService;


    //    查找所有id不为null的优惠券
    @Override
    public List<Wangminyi_Coupon> queryAllCoupons(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        Wangminyi_CouponExample couponExample = new Wangminyi_CouponExample();
        couponExample.createCriteria().andIdIsNotNull();
        List<Wangminyi_Coupon> coupons = couponMapper.selectByExample(couponExample);
        return coupons;
    }

    //    把coupon转换为响应需要的couponForListVo
    @Override
    public List<CouponForListVo> couponToCouponForListVo(List<Wangminyi_Coupon> coupons) {
        List<CouponForListVo> couponVos = new ArrayList();
        for (Wangminyi_Coupon coupon : coupons) {
            CouponForListVo couponVo = new CouponForListVo();
            couponVo.setDays(coupon.getDays());
            couponVo.setDesc(coupon.getDesc());
            couponVo.setDiscount(coupon.getDiscount());
            couponVo.setId(coupon.getId());
            couponVo.setMin(coupon.getMin());
            couponVo.setName(coupon.getName());
            couponVo.setTag(coupon.getTag());
            couponVos.add(couponVo);
        }
        return couponVos;
    }

    //    查看我的优惠券(status：0未使用   1已使用    2已过期)
    @Override
    public List<Wangminyi_Coupon_User> queryMyCoupons(Short status) {

        Wangminyi_Coupon_UserExample couponUserExample = new Wangminyi_Coupon_UserExample();
        couponUserExample.createCriteria().andStatusEqualTo(status);
        List<Wangminyi_Coupon_User> couponUsers = couponUserMapper.selectByExample(couponUserExample);

        return couponUsers;
    }

    //    查看我的优惠券(goodstype：0通用券   1指定分类券    2指定商品券)
    public List<Wangminyi_Coupon_User> queryMyCouponsByGoodstype(Short goodstype) {
        List<Wangminyi_Coupon_User> couponUserResult = new ArrayList<>();
        //从couponuser表里查找我的所有优惠券
        Wangminyi_Coupon_UserExample couponUserExample = new Wangminyi_Coupon_UserExample();
        couponUserExample.createCriteria().andIdIsNotNull();
        List<Wangminyi_Coupon_User> couponUsers = couponUserMapper.selectByExample(couponUserExample);

        //从coupon表里查找我的优惠券对应的记录,把与查询的goodstype相符的记录返回
        List<Wangminyi_Coupon> coupons = this.queryCouponsByCouponUser(couponUsers);
        for (Wangminyi_Coupon coupon : coupons) {
            if (coupon.getGoodsType() == goodstype) {
                Wangminyi_Coupon_User couponUser = this.couponToCouponUser(coupon);
                couponUserResult.add(couponUser);
            }
        }
        return couponUserResult;

    }


    //    根据couponuser表内的优惠券id，在coupon表内查询对应的优惠券
    //    因为cskaoyanmall_coupon_user内缺少CouponForMylistVo中的desc属性?
    @Override
    public List<Wangminyi_Coupon> queryCouponsByCouponUser(List<Wangminyi_Coupon_User> couponUsers) {
        if (couponUsers == null) {
            return null;
        }
        List<Integer> idList = new ArrayList<>();

        for (Wangminyi_Coupon_User couponUser : couponUsers) {
            idList.add(couponUser.getCouponId());
        }
        if (idList.size() == 0) {
            return null;
        }
        Wangminyi_CouponExample couponExample = new Wangminyi_CouponExample();
        couponExample.createCriteria().andIdIn(idList);
        List<Wangminyi_Coupon> coupons = couponMapper.selectByExample(couponExample);
        return coupons;
    }


    //    把coupon转换为响应需要的couponForMylistVo
    @Override
    public List<CouponForMylistVo> couponToCouponForMyListVo(List<Wangminyi_Coupon> coupons) {
        if (coupons == null) {
            return null;
        }
        List<CouponForMylistVo> couponForMylistVos = new ArrayList<>();
        for (Wangminyi_Coupon coupon : coupons) {
            CouponForMylistVo couponForMylistVo = new CouponForMylistVo();
            couponForMylistVo.setDesc(coupon.getDesc());
            couponForMylistVo.setDiscount(coupon.getDiscount());
            couponForMylistVo.setEndTime(coupon.getEndTime());
            couponForMylistVo.setId(coupon.getId());
            couponForMylistVo.setMin(coupon.getMin());
            couponForMylistVo.setName(coupon.getName());
            couponForMylistVo.setStartTime(coupon.getStartTime());
            couponForMylistVo.setTag(coupon.getTag());
            couponForMylistVos.add(couponForMylistVo);
        }
        return couponForMylistVos;
    }


    //    根据couponId在coupon表中查询对应的一张优惠券
    @Override
    public Wangminyi_Coupon queryCouponById(Integer couponId) {
        Wangminyi_CouponExample couponExample = new Wangminyi_CouponExample();

        couponExample.createCriteria().andIdEqualTo(couponId);
        Wangminyi_Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
//        List<Wangminyi_Coupon> coupons = couponMapper.selectByExample(couponExample);
//        Wangminyi_Coupon coupon = coupons.get(0);
        return coupon;
    }


    //    根据couponId(couponUser表内的couponId对应coupon表内的id)
    //    在couponUser表中查询对应的(若干张)优惠券
    @Override
    public List<Wangminyi_Coupon_User> queryCouponUserByCouponId(Integer couponId) {
        Wangminyi_Coupon_UserExample couponUserExample = new Wangminyi_Coupon_UserExample();
        couponUserExample.createCriteria().andCouponIdEqualTo(couponId);
        List<Wangminyi_Coupon_User> couponUsers = couponUserMapper.selectByExample(couponUserExample);
        return couponUsers;
    }


    //    将coupon转换为couponUser
    @Override
    public Wangminyi_Coupon_User couponToCouponUser(Wangminyi_Coupon coupon) {
        //方法的形参缺orderid      未区分addtime与updatetime
        Wangminyi_Coupon_User couponUser = new Wangminyi_Coupon_User(null, goodsService.getUserId(), coupon.getId(),
                coupon.getStatus(), null, coupon.getStartTime(), coupon.getEndTime(),
                null, new Date(), new Date(), false);
        return couponUser;

    }

    //    获得优惠券(领取)
    @Override
    public BaseRespVo receiveAndExchangeCoupon(Integer id) {
        //查找待领取的优惠券
        Wangminyi_Coupon coupon = this.queryCouponById(id);


        //已领完则返回740
        int temp = coupon.getTotal();
        if (temp == 0) {
            return new BaseRespVo(null, "优惠券已领完", 740);
        }

        //对于限领1张的优惠券，判断待领取的优惠券是否已存在
        if (coupon.getLimit() == 1) {
            List<Wangminyi_Coupon_User> couponUsers = this.queryCouponUserByCouponId(coupon.getId());
            //已领取则返回740
            if (couponUsers.size() > 0) {
                return new BaseRespVo(null, "优惠券已经领取过", 740);
            }
        }

        Wangminyi_Coupon_User couponUser = this.couponToCouponUser(coupon);
        couponUserMapper.insert(couponUser);

        //领取后优惠券减一
        coupon.setTotal(--temp);
        couponMapper.updateByPrimaryKey(coupon);
        return BaseRespVo.ok();

    }


    //    获得优惠券(兑换)
    @Override
    public BaseRespVo receiveAndExchangeCoupon(String code) {
        //根据兑换码查询优惠券id，再调用重载的方法
        Wangminyi_CouponExample couponExample = new Wangminyi_CouponExample();
        couponExample.createCriteria().andCodeEqualTo(code);
        Integer id = couponMapper.selectByExample(couponExample).get(0).getId();
        return this.receiveAndExchangeCoupon(id);
    }

    @Override
    public Wangminyi_Coupon queryCouponByCode(String code) {
        Wangminyi_CouponExample couponExample = new Wangminyi_CouponExample();
        couponExample.createCriteria().andCodeEqualTo(code);
        List<Wangminyi_Coupon> coupons = couponMapper.selectByExample(couponExample);
        if (coupons.size() == 0) {
            return null;
        }
        Wangminyi_Coupon coupon = coupons.get(0);
        return coupon;
    }


    //从我的优惠券内选择当前订单可用的优惠券
    @Override
    public List<CouponForMylistVo> queryMyCouponsForSelectlist(List<GoodsForQueryCoupon> goodsForQueryCouponList) {
        //判断用户有没有优惠券，没有直接返回null
        List<Wangminyi_Coupon_User> couponUsers = new ArrayList<>();
        List<Wangminyi_Coupon_User> tempCouponUsersStatus0 = this.queryMyCouponsByGoodstype((short) 0);
        List<Wangminyi_Coupon_User> tempCouponUsersStatus1 = this.queryMyCouponsByGoodstype((short) 1);
        List<Wangminyi_Coupon_User> tempCouponUsersStatus2 = this.queryMyCouponsByGoodstype((short) 2);
        couponUsers.addAll(tempCouponUsersStatus0);
        couponUsers.addAll(tempCouponUsersStatus1);
        couponUsers.addAll(tempCouponUsersStatus2);
        if (couponUsers.size() == 0) {
            return null;
        }

        //统计可用的优惠券
        List<Wangminyi_Coupon> workableCoupons = new ArrayList<>();

        int length = goodsForQueryCouponList.size();
        //如果用户有type = 0 的通用优惠券，根据商品总价先对其进行判断
        if (tempCouponUsersStatus0.size() > 0) {

            //各个商品的数量乘以价格的总价。totalPrice的最后一位存储所有商品的总价
            int totalPrice = 0;
            for (int i = 0; i < length; i++) {
                totalPrice += goodsForQueryCouponList.get(i).getPrice() * goodsForQueryCouponList.get(i).getGoodsNumber();
            }

            //如果商品总价超过了通用优惠券的使用限制,则把该优惠券增加至统计结果
            List<Wangminyi_Coupon> couponList = this.queryCouponsByCouponUser(tempCouponUsersStatus0);
            for (Wangminyi_Coupon coupon : couponList) {
                if (coupon.getMin().intValue() < totalPrice) {
                    workableCoupons.add(coupon);
                }
            }
        }


        //如果用户有type = 1 的分类优惠券，根据商品分类的总价对其进行判断
        if (tempCouponUsersStatus1.size() > 0) {

            //统计分类优惠券的可用分类
            Map<Integer, Integer> map = new HashMap<>();
            List<Wangminyi_Coupon> couponList = this.queryCouponsByCouponUser(tempCouponUsersStatus1);
            for (Wangminyi_Coupon coupon : couponList) {

                //type = 1 的分类优惠券，goodsValue里存储的是类目的String集合？
                //---------------------------------------------------------------
                //能力有限，按类目的String集合实现实现不了。只考虑只有一个String的情况
                //---------------------------------------------------------------
                String tempString = coupon.getGoodsValue();

                //此处根据结果有没有引号改 -2
                Integer goodsCategoryId = Integer.valueOf(tempString.substring(1, tempString.length() - 1));
                if (!map.containsKey(goodsCategoryId)) {
                    map.put(goodsCategoryId, 0);
                }
            }

            //判断订单商品的分类是否在优惠券的可用分类内，有则存值
            for (GoodsForQueryCoupon goodsForQueryCoupon : goodsForQueryCouponList) {
                Integer goodsCategoryId = goodsForQueryCoupon.getGoodsCategoryId();
                if (map.containsKey(goodsCategoryId)) {
                    Integer sum = goodsForQueryCoupon.getGoodsNumber() * goodsForQueryCoupon.getPrice();
                    Integer temp = map.get(goodsCategoryId);
                    map.put(goodsCategoryId, temp + sum);
                }
            }

            //遍历map，如果value不为0，说明此分类有可用优惠券
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() > 0) {
                    //判断适用于该商品分类的优惠券的最低使用金额是否小于订单内该商品分类的总金额
                    List<Wangminyi_Coupon> tempCouponList = this.queryCouponByCategoryId(entry.getKey());
                    if (tempCouponList.size() == 0) {
                        break;
                    }
                    if (tempCouponList.get(0).getDiscount().intValue() < entry.getValue()) {
                        workableCoupons.add(tempCouponList.get(0));
                    }
                }
            }
        }


        //如果用户有type = 2 的商品优惠券，根据对应商品的总价对其进行判断
        if (tempCouponUsersStatus2.size() > 0) {

            //统计商品优惠券的可用商品
            Map<Integer, Integer> map = new HashMap<>();
            List<Wangminyi_Coupon> couponList = this.queryCouponsByCouponUser(tempCouponUsersStatus2);
            for (Wangminyi_Coupon coupon : couponList) {
                String tempString = coupon.getGoodsValue();
                Integer goodsId = Integer.valueOf(tempString.substring(1, tempString.length() - 1));
                if (!map.containsKey(goodsId)) {
                    map.put(goodsId, 0);
                }
            }
            //判断订单商品的id是否在优惠券的可用商品id内，有则存值
            for (GoodsForQueryCoupon goodsForQueryCoupon : goodsForQueryCouponList) {
                Integer goodsId = goodsForQueryCoupon.getGoodsId();
                if (map.containsKey(goodsId)) {
                    Integer sum = goodsForQueryCoupon.getGoodsNumber() * goodsForQueryCoupon.getPrice();
                    Integer temp = map.get(goodsId);
                    map.put(goodsId, temp + sum);
                }
            }

            //遍历map，如果value不为0，说明此商品有可用优惠券
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() > 0) {
                    //判断适用于该商品的优惠券的最低使用金额是否小于订单内该商品的总金额
                    Wangminyi_CouponExample couponExample = new Wangminyi_CouponExample();
                    couponExample.createCriteria().andGoodsValueLike("%" + entry.getKey() + "%");
                    List<Wangminyi_Coupon> coupon = couponMapper.selectByExample(couponExample);
                    if (coupon.get(0).getDiscount().intValue() < entry.getValue()) {
                        workableCoupons.add(coupon.get(0));
                    }
                }
            }
        }
        return this.couponToCouponForMyListVo(workableCoupons);
    }

    @Override
    public List<Wangminyi_Coupon> queryCouponByCategoryId(Integer categoryId) {
        Wangminyi_CouponExample couponExample = new Wangminyi_CouponExample();
        couponExample.createCriteria().andGoodsValueLike(categoryId + "");
        return couponMapper.selectByExample(couponExample);
    }


}
