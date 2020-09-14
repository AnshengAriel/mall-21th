package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.bean_of_hanyuandong.wx.CommentBo;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.wx.OrderDetailVo_Han;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.wx.OrderGoods_Han;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.wx.OrderListVo;

public interface OrderService_Wx {
    OrderListVo queryOrderDataList(Integer showType, Integer page, Integer size);

    OrderDetailVo_Han queryOrderDetailByOrderId(Integer orderId);

    void confirmOrder(Integer orderId);

    OrderGoods_Han getOrderGoods(Integer orderId, Integer goodsId);

    void commentOrder(CommentBo comment);

    void logicDeleteOrderById(Integer orderId);

    void cancelOrderByOrderId(Integer orderId);

    void OrderRefund(Integer orderId);




}
