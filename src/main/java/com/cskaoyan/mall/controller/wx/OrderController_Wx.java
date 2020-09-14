package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.bean.BaseRespVo;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.Storage;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.wx.*;
import com.cskaoyan.mall.service.OrderService_Wx;
import com.cskaoyan.mall.service.StorageService;
import com.cskaoyan.mall.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("wx/order/")
public class OrderController_Wx {

    @Autowired
    OrderService_Wx orderService;

    //显示订单，需要userID
    @RequestMapping("list")
    public BaseRespVo orderList(Integer showType,Integer page,Integer size) {
        OrderListVo orderListVo = orderService.queryOrderDataList(showType,page,size);
        return BaseRespVo.ok(orderListVo);
    }

    @RequestMapping("detail")
    public BaseRespVo orderDetail(Integer orderId){
        OrderDetailVo_Han orderDetail = orderService.queryOrderDetailByOrderId(orderId);
        return BaseRespVo.ok(orderDetail);
    }

    @PostMapping("confirm")
    public BaseRespVo confirmOrder(@RequestBody OrderIdBo orderIdBo) {
        Integer orderId = orderIdBo.getOrderId();
        orderService.confirmOrder(orderId);
        return BaseRespVo.ok();
    }

    @RequestMapping("goods")
    public BaseRespVo orderGoodsInfo(Integer orderId, Integer goodsId){
        OrderGoods_Han orderGoods = orderService.getOrderGoods(orderId, goodsId);
        return BaseRespVo.ok(orderGoods);
    }

    @PostMapping("comment")
    public BaseRespVo commentOrder(@RequestBody CommentBo comment) {
        orderService.commentOrder(comment);
        return BaseRespVo.ok();
    }

    @PostMapping("delete")
    public BaseRespVo logicDeleteOrder(@RequestBody OrderIdBo deleteBo) {
        Integer orderId = deleteBo.getOrderId();
        orderService.logicDeleteOrderById(orderId);
        return BaseRespVo.ok();
    }

    @PostMapping("cancel")
    public BaseRespVo cancelOrder(@RequestBody OrderIdBo orderIdBo) {
        Integer orderId = orderIdBo.getOrderId();
        orderService.cancelOrderByOrderId(orderId);
        return BaseRespVo.ok();
    }

    @PostMapping("refund")
    public BaseRespVo refund(@RequestBody OrderIdBo orderIdBo){
        Integer orderId = orderIdBo.getOrderId();
        orderService.OrderRefund(orderId);
        return BaseRespVo.ok();
    }



}
