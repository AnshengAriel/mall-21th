package com.cskaoyan.mall.controller.controller_weixin_liuxufeng;

import com.cskaoyan.mall.bean.BaseRespVo;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.*;
import com.cskaoyan.mall.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("wx/cart/")
public class WxCartController {

    @Autowired
    CartServiceImpl cartService;

    @GetMapping("index")
    public BaseRespVo cartIndex(){
        CartListVo cartListVo = cartService.cartIndex();
        return BaseRespVo.ok(cartListVo);
    }

    @PostMapping("update")
    public BaseRespVo updateCart(@RequestBody CartProductBo cartProductBo){
        cartService.updateCart(cartProductBo);
        return BaseRespVo.ok();
    }

    @PostMapping("checked")
    public BaseRespVo checkedCart(@RequestBody CheckedBo checkedBo){
        CartListVo cartListVo = cartService.checkedCart(checkedBo);
        return BaseRespVo.ok(cartListVo);
    }

    @PostMapping("delete")
    public BaseRespVo deleteCart(@RequestBody ProductIds productIds){
        CartListVo cartListVo = cartService.deleteCartByProductIds(productIds);
        return BaseRespVo.ok(cartListVo);
    }

    /**
     * 需要修改
     * @return 根据user返回对应购物车数量
     */
    @GetMapping("goodscount")
    public BaseRespVo goodsCountCart(){
        Integer i = cartService.getGoodsCount();
        return BaseRespVo.ok(i);
    }

    /**
     * 需要修改
     * 根据user给购物车添加商品
     * @return
     */
    @PostMapping("add")
    public BaseRespVo addCountCart(@RequestBody AddCartBo addCartBo){
        int cartId = cartService.addCountCart(addCartBo);
        return goodsCountCart();
    }

    @PostMapping("fastadd")
    public BaseRespVo fastAddCart(@RequestBody AddCartBo addCartBo){
        int cartId = cartService.addCountCart(addCartBo);
        return BaseRespVo.ok(cartId);
    }

    @GetMapping("checkout")
    public BaseRespVo checkoutCart(CheckedOutBo checkedOutBo){
        Map map = cartService.checkedoutCart(checkedOutBo);
        return BaseRespVo.ok(map);
    }
}
