package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.bean_of_liuxufeng.*;
import com.cskaoyan.mall.bean.bean_of_tangronghua.Address;
import com.cskaoyan.mall.bean.bean_of_tangronghua.AddressExample;
import com.cskaoyan.mall.bean.bean_of_tangronghua.User;
import com.cskaoyan.mall.bean.bean_of_tangronghua.UserExample;
import com.cskaoyan.mall.bean.bean_of_wangminyi.Goods;
import com.cskaoyan.mall.bean.bean_of_wangminyi.GoodsProduct;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.CheckedGoods;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.CouponForMylistVo;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.GoodsForQueryCoupon;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.service.CartService;
import com.cskaoyan.mall.service.service_weixin_wangminyi.Wangminyi_CouponService;
import com.cskaoyan.mall.service.service_weixin_wangminyi.Wangminyi_GoodsService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartMapper cartMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    CouponUserMapper couponUserMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    CouponMapper couponMapper;

    //wmy
    @Autowired
    Wangminyi_GoodsService goodsService;
    @Autowired
    Wangminyi_CouponService wangminyiCouponService;
    @Autowired
    UserMapper userMapper;

    private Integer getUserId() {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipals().getPrimaryPrincipal();
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        return users.get(0).getId();
    }

    /**
     * 此处逻辑不完全，取的是userId = 1 的订单
     *
     * @return
     */
    @Override
    public CartListVo cartIndex() {
        CartListVo cartListVo = new CartListVo();
        CartExample cartExample = new CartExample();
        cartExample.createCriteria().andUserIdEqualTo(getUserId()).andDeletedEqualTo(false);
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        List<CartListBean> cartListBeans = new ArrayList<>();
        int goodsCount = 0, checkedGoodsCount = 0;
        for (Cart cart : carts
        ) {
            goodsCount += cart.getNumber();
            if (cart.getChecked()) {
                checkedGoodsCount++;
            }
            CartListBean cartListBean = new CartListBean();
            cartListBean = cart.change2CartListBean(cartListBean);
            cartListBeans.add(cartListBean);
        }
        CartListVo.CartTotalBean cartTotalBean = new CartListVo.CartTotalBean();
        cartTotalBean.setGoodsCount(goodsCount);
        cartTotalBean.setCheckedGoodsCount(checkedGoodsCount);
        cartListVo.setCartTotal(cartTotalBean);
        cartListVo.setCartList(cartListBeans);
        return cartListVo;
    }

    @Override
    public void updateCart(CartProductBo cartProductBo) {
        Cart cart = cartMapper.selectByPrimaryKey(cartProductBo.getId());
        if (cart == null) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        cart.setGoodsId(cartProductBo.getGoodsId());
        cart.setProductId(cartProductBo.getProductId());
        cart.setNumber(cartProductBo.getNumber());
        cartMapper.updateByPrimaryKey(cart);
    }

    /**
     * 实现逻辑太简单了，查询次数过多。List很长的话就查询很多次了...
     *
     * @param checkedBo
     * @return
     */
    @Override
    public CartListVo checkedCart(CheckedBo checkedBo) {
        List<Integer> productIds = checkedBo.getProductIds();
        for (Integer i : productIds
        ) {
            CartExample cartExample = new CartExample();
            cartExample.createCriteria().andProductIdEqualTo(i);
            List<Cart> carts = cartMapper.selectByExample(cartExample);
            Cart cart = carts.get(0);
            cart.setChecked(checkedBo.isIsChecked());
            cartMapper.updateByPrimaryKey(cart);
        }
        return this.cartIndex();
    }

    @Override
    public CartListVo deleteCartByProductIds(ProductIds productIds) {
        for (Integer i : productIds.getProductIds()
        ) {
            CartExample cartExample = new CartExample();
            cartExample.createCriteria().andProductIdEqualTo(i);
            cartMapper.deleteByExample(cartExample);
        }
        return this.cartIndex();
    }

    /**
     * 根据当前userId （当前登录的人的购物数量进行统计）
     *
     * @return
     */
    @Override
    public Integer getGoodsCount() {
        CartExample cartExample = new CartExample();
        cartExample.createCriteria().andUserIdEqualTo(getUserId()).andDeletedEqualTo(false);
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        int goodsCount = 0;
        for (Cart cart : carts
        ) {
            if (cart.getChecked()) goodsCount += cart.getNumber();
        }
        return goodsCount;
    }

    @Override
    public int addCountCart(AddCartBo addCartBo) {
        //先判断一下购物车有没有此商品，有的话，改变一下number + ，以及改变一下updatetime就可以了
        int goodsId = addCartBo.getGoodsId();
        Short number = addCartBo.getNumber();
        int productId = addCartBo.getProductId();
        CartExample cartExample = new CartExample();
        //此处还要对应 userId 的逻辑
        cartExample.createCriteria().andGoodsIdEqualTo(goodsId).andProductIdEqualTo(productId).andUserIdEqualTo(getUserId()).andDeletedEqualTo(false);
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        if (carts.size() != 0) {
            Cart cart = carts.get(0);
            cart.setNumber((short) (cart.getNumber() + number));
            cart.setUpdateTime(new Date());
            cartMapper.updateByPrimaryKeySelective(cart);
            return cart.getId();
        } else {
            Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
            GoodsProduct goodsProduct = goodsProductMapper.selectByPrimaryKey(productId);
            Cart cart = new Cart(goods, goodsProduct, number);
            int insert = cartMapper.insert(cart);
            return cart.getId();
        }
    }

    @Override
    public Map checkedoutCart(CheckedOutBo checkedOutBo) {
        Map<String, Object> map = new HashMap<>();
        CartExample cartExample = new CartExample();
        //先拿到所有的购物车订单
        if (checkedOutBo.getCartId() == 0) {
            cartExample.createCriteria().andCheckedEqualTo(true).andUserIdEqualTo(getUserId()).andDeletedEqualTo(false);
        } else {
            cartExample.createCriteria().andIdEqualTo(checkedOutBo.getCartId());
        }//到这里为止，要么就是全部的订单都搞出来了，要么就只有一件

        List<Cart> carts = cartMapper.selectByExample(cartExample);
        int goodsTotalPrice = 0;
        for (Cart c : carts
        ) {
            goodsTotalPrice += c.getPrice().intValue() * c.getNumber().intValue();
        }

        //查找优惠券
        //CouponUserExample couponUserExample = new CouponUserExample();
        //Short s = 0;
        //couponUserExample.createCriteria().andUserIdEqualTo(1).andStatusEqualTo(s);
        //List<CouponUser> couponUsers = couponUserMapper.selectByExample(couponUserExample);



        //wmy
        //获取订单内商品的可用优惠券列表couponForMylistVo
        List<GoodsForQueryCoupon> goodsForQueryCouponList = goodsService.cartsToGoodsForQueryCoupon(carts);
        List<CouponForMylistVo> couponForMylistVo = wangminyiCouponService.queryMyCouponsForSelectlist(goodsForQueryCouponList);
        int availableCouponLength = couponForMylistVo.size();

//        int availableCouponLength = couponUsers.size();
        //获得地址对象
//        Integer addressId = checkedOutBo.getAddressId();
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andUserIdEqualTo(getUserId());
        List<Address> addresses = addressMapper.selectByExample(addressExample);
        Address address = addresses.get(0);

        //wmy
        //couponPrice：显示可用优惠券的最大优惠金额
        int couponPrice=0;
        int couponId  = 0;
        for (CouponForMylistVo forMylistVo : couponForMylistVo) {
            if(forMylistVo.getDiscount().intValue()>couponPrice){
                couponPrice = forMylistVo.getDiscount().intValue();
                couponId = forMylistVo.getId();
            }
        }
//        Integer couponId = checkedOutBo.getCouponId();



        if (couponId != 0) {
            Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
            couponPrice = coupon.getDiscount().intValue();
        }
        Integer grouponRulesId = checkedOutBo.getGrouponRulesId(); //团购的

        int freightPrice = 0;  //运费不需要
        int actualPrice = goodsTotalPrice - couponPrice;
        map.put("actualPrice", actualPrice);
        map.put("addressId", address.getId());  //暂时用5
        map.put("availableCouponLength", availableCouponLength);
        map.put("checkedAddress", address);
        map.put("checkedGoodsList", carts);
        map.put("couponId", couponId);
        map.put("couponPrice", couponPrice);
        map.put("freightPrice", freightPrice);
        map.put("goodsTotalPrice", goodsTotalPrice);
        map.put("grouponPrice", 0);
        map.put("grouponRulesId", 0);
        map.put("orderTotalPrice", actualPrice);

        return map;
    }



    //wmy
    public List<Cart> queryAvailableCouponsForCart(Integer cartId) {

        //上面的checkedoutCart里复制下来的

        Map<String, Object> map = new HashMap<>();
        CartExample cartExample = new CartExample();
        //先拿到所有的购物车订单
        if (cartId == 0) {
            cartExample.createCriteria().andCheckedEqualTo(true).andUserIdEqualTo(getUserId()).andDeletedEqualTo(false);
        } else {
            cartExample.createCriteria().andIdEqualTo(cartId);
        }//到这里为止，要么就是全部的订单都搞出来了，要么就只有一件

        List<Cart> carts = cartMapper.selectByExample(cartExample);
        return carts;
    }

}
