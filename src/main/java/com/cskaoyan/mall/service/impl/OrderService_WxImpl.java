package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.bean_of_hanyuandong.wx.*;
import com.cskaoyan.mall.bean.bean_of_tangronghua.User;
import com.cskaoyan.mall.bean.bean_of_tangronghua.UserExample;
import com.cskaoyan.mall.mapper.CommentHMapper;
import com.cskaoyan.mall.mapper.OrderGoodsMapper_han;
import com.cskaoyan.mall.mapper.OrderMapper_han;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.OrderService_Wx;
import com.cskaoyan.mall.utils.TypeConverter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class OrderService_WxImpl implements OrderService_Wx {
    @Autowired
    OrderMapper_han orderMapper;
    @Autowired
    OrderGoodsMapper_han orderGoodsMapper;
    @Autowired
    CommentHMapper commentHMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public OrderListVo queryOrderDataList(Integer showType, Integer page, Integer size) {
        //先要获取userid,必须要知道是谁的订单
        Integer userId = getUserId();

        //先分页
        PageHelper.startPage(page,size);
        //根据showType的不同获取不同的查询条件
<<<<<<< HEAD
        OrderExample orderExample = createOrderExampleByShowType(showType);
        //------------第一次，查询Order-------------
=======
        OrderExample orderExample = createOrderExampleByShowType(showType,userId);
        //-----------第一次，查询Order--------------
>>>>>>> 5ff8d28b57fc06ac3958fc246de0d27b0385c7b1
        List<Order> orderList = orderMapper.selectByExample(orderExample);
        List<OrderData> orderDatas = new ArrayList<>();
        //将查询结果封装到一个List<OrderData>中
        orderListToOrderDataList(orderList,orderDatas);

        //-----------第二次，查询orderGoods--------------
        OrderGoodsExample_Han orderGoodsExample = new OrderGoodsExample_Han();
        orderGoodsExample.createCriteria().andDeletedEqualTo(false);
        List<OrderGoods_Han> orderGoodsList = orderGoodsMapper.selectByExample(orderGoodsExample);

        //将查询到的OrderGoods封装到OrderData里
        encapsulationOrderGoods(orderDatas, orderGoodsList);

        PageInfo<Order> pageInfo = new PageInfo<>(orderList);
        Long count = pageInfo.getTotal();
        Long totalPages = count/size;
        OrderListVo orderListVo = new OrderListVo(count, orderDatas, totalPages);
        return orderListVo;
    }


    @Override
    public OrderDetailVo_Han queryOrderDetailByOrderId(Integer orderId) {
    //第一次，查询order
        Order order = orderMapper.selectByPrimaryKey(orderId);
        String orderStatusText = getOrderStatusText(order);
        HandleOptionVo handleOptionVo = getHandleOption(order);
        OrderInfo orderInfo = new OrderInfo(order.getId(), order.getOrderSn(), orderStatusText, order.getConsignee(), order.getMobile(), order.getAddress(), order.getGoodsPrice(), order.getFreightPrice(), order.getCouponPrice(), order.getActualPrice(), order.getAddTime(), handleOptionVo);
    //第二次，查询orderGoods
        OrderGoodsExample_Han example = new OrderGoodsExample_Han();
        example.createCriteria().andOrderIdEqualTo(orderId);
        List<OrderGoods_Han> orderGoods = orderGoodsMapper.selectByExample(example);
        return new OrderDetailVo_Han(orderGoods,orderInfo);
    }

    @Override
    public void confirmOrder(Integer orderId) {
        Date date = new Date(System.currentTimeMillis());
        orderMapper.updateStatusById(orderId,401,date);
    }

    @Override
    public OrderGoods_Han getOrderGoods(Integer orderId, Integer goodsId) {
        OrderGoodsExample_Han example = new OrderGoodsExample_Han();
        example.createCriteria().andOrderIdEqualTo(orderId).andGoodsIdEqualTo(goodsId);
        List<OrderGoods_Han> orderGoodsList = orderGoodsMapper.selectByExample(example);
        return orderGoodsList.get(0);
    }

    @Override
    public void commentOrder(CommentBo comment) {
        //是否有图片
        List<String> picUrls = comment.getPicUrls();
        boolean hasPicture = false;
        if (picUrls.size() > 0) hasPicture = true;
        //图片转化为json字符串
        String s = TypeConverter.parseS(comment.getPicUrls());
        //添加时间，更新时间
        Date date = new Date(System.currentTimeMillis());

        Integer userId = getUserId();
        CommentH commentH = new CommentH(null, comment.getOrderGoodsId(), (byte) 3, comment.getContent(), userId, hasPicture, s, (short) comment.getStar().intValue(), date, date, false);
        int id = commentHMapper.insert(commentH);

        //先根据OrderGoodsId查询OrderGoods表，查到orderId
        Integer orderId = orderGoodsMapper.selectOrderIdByOrderGoodsId(comment.getOrderGoodsId());
        //再根据orderId更新comment字段
        orderMapper.updateOrderCommentById(orderId);

        //更新orderGoods表的comment字段
        OrderGoodsExample_Han orderGoodsExample = new OrderGoodsExample_Han();
        orderGoodsExample.createCriteria().andIdEqualTo(comment.getOrderGoodsId());
        OrderGoods_Han orderGoodsHan = new OrderGoods_Han(null,null,null,null,null,null,null,null,null,null,id,null,date,false);
        orderGoodsMapper.updateByExampleSelective(orderGoodsHan,orderGoodsExample);
    }

    @Override
    public void logicDeleteOrderById(Integer orderId) {
        orderMapper.logicDeleteOrderById(orderId);
        orderGoodsMapper.logicDeleteOrderGoodsByOrderId(orderId);
    }

    @Override
    public void cancelOrderByOrderId(Integer orderId) {
        orderMapper.updateOrderStatusById(orderId,102);
    }

    @Override
    public void OrderRefund(Integer orderId) {
        orderMapper.updateOrderStatusById(orderId,202);
    }


    private HandleOptionVo getHandleOption(Order order) {
        HandleOptionVo optionVo = new HandleOptionVo();
        Short orderStatus = order.getOrderStatus();
        int i = orderStatus.intValue();
        switch (i) {
            case 101 :{
                optionVo.setCancel(true);
                optionVo.setPay(true);
                break;
            }
            case 201 :{
                optionVo.setRefund(true);
                break;
            }
            case 301 :{
                optionVo.setConfirm(true);
                break;
            }
            case 401 :{
                optionVo.setComment(true);
                optionVo.setDelete(true);
                optionVo.setRebuy(true);
                break;
            }
            case 402 :{
                optionVo.setComment(true);
                optionVo.setDelete(true);
                optionVo.setRebuy(true);
                break;
            }
            case 102 :{
                optionVo.setComment(true);
                optionVo.setDelete(true);
                optionVo.setRebuy(true);
                break;
            }
            case 103 :{
                optionVo.setComment(true);
                optionVo.setDelete(true);
                optionVo.setRebuy(true);
                break;
            }
        }
        return optionVo;
    }

    private void encapsulationOrderGoods(List<OrderData> orderDatas, List<OrderGoods_Han> orderGoodsList) {
        for (int i = 0; i < orderDatas.size(); i++) {

            Iterator<OrderGoods_Han> iterator = orderGoodsList.iterator();
            List<GoodsInfo> goodsInfoList = new ArrayList<>();
            while (iterator.hasNext()){
                OrderGoods_Han next = iterator.next();
                if (next.getOrderId().equals(orderDatas.get(i).getId())){
                    //OrderGoods_Han  ——》  GoodsInfo
                    GoodsInfo goodsInfo = createGoodsInfoByOrderGoodsHan(next);
                    //将GoodsInfo对象添加到对应的GoodsInfoList里
                    goodsInfoList.add(goodsInfo);
                    //将OrderGoods_Han从list中删除
                    iterator.remove();
                }
            }
            orderDatas.get(i).setGoodsList(goodsInfoList);

        }
    }

    /**
     *  orderGoods   ——》   GoodsInfo
     * @param orderGoods
     */
    private GoodsInfo createGoodsInfoByOrderGoodsHan(OrderGoods_Han orderGoods) {
        GoodsInfo goodsInfo = new GoodsInfo(orderGoods.getGoodsName(), orderGoods.getGoodsId(), orderGoods.getNumber().intValue(), orderGoods.getPicUrl());
        return goodsInfo;
    }

    /**
     * 将List<Order> ——》 List<OrderData>
     * @param orderList
     * @param orderDatas
     */
    private void orderListToOrderDataList(List<Order> orderList, List<OrderData> orderDatas) {
        for (Order order : orderList) {
            String orderStatusText = getOrderStatusText(order);
            boolean isGroupin = false;
            if (order.getGrouponPrice().intValue() > 0) {
                isGroupin = true;
            }
            HandleOptionVo handleOption = getHandleOption(order);
            OrderData orderData = new OrderData(order.getActualPrice().intValue(), null, handleOption, order.getId(), isGroupin, order.getOrderSn(), orderStatusText);
            orderDatas.add(orderData);
        }
    }

    private String getOrderStatusText(Order order) {
        int i = order.getOrderStatus().intValue();
        switch (i) {
            case 101 : {
                return "未付款";
            }
            case 102 : {
                return "用户取消";
            }
            case 103 : {
                return "已评价";
            }
            case 201 : {
                return "已付款";
            }
            case 202 : {
                return "申请退款";
            }
            case 203 : {
                return "已退款";
            }
            case 301 : {
                return "已发货";
            }
            case 401 : {
                return "用户收货";
            }
            case 402 : {
                return "系统收货";
            }
        }
        return "出错了";
    }

    /**
     * 根据showType的不同创建不同的查询条件
     * @param showType
     * @return
     */
    private OrderExample createOrderExampleByShowType(Integer showType,Integer userId) {

        OrderExample orderExample = new OrderExample();
        //orderExample.createCriteria().andDeletedEqualTo(false);
        if (showType == null || showType == 0) {
            orderExample.createCriteria().andDeletedEqualTo(false).andUserIdEqualTo(userId);
            return orderExample;
        }
        int i = showType;
        switch (i) {
            case 1: {
                orderExample.createCriteria().andOrderStatusEqualTo((short)101).andDeletedEqualTo(false).andUserIdEqualTo(userId);
                break;
            }
            case 2: {
                orderExample.createCriteria().andOrderStatusEqualTo((short)201).andDeletedEqualTo(false).andUserIdEqualTo(userId);
                break;
            }
            case 3: {
                orderExample.createCriteria().andOrderStatusEqualTo((short)301).andDeletedEqualTo(false).andUserIdEqualTo(userId);
                break;
            }
            case 4: {
                List<Short> shorts = new ArrayList<>();
                shorts.add((short)401);
                shorts.add((short)402);
                orderExample.createCriteria().andOrderStatusIn(shorts).andDeletedEqualTo(false).andUserIdEqualTo(userId);
                break;
            }
        }
        return orderExample;
    }

    private Integer getUserId() {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipals().getPrimaryPrincipal();
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        return users.get(0).getId();
    }
}
