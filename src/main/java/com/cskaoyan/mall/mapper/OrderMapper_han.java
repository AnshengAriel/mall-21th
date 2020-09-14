package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.bean_of_hanyuandong.wx.Order;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.wx.OrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderMapper_han {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    void updateStatusById(@Param("id") Integer orderId, @Param("status") Integer status, @Param("updateTime")Date date);

    void updateOrderCommentById(@Param("id") Integer orderId);

    void logicDeleteOrderById(@Param("orderId") Integer orderId);

    void updateOrderStatusById(@Param("orderId") Integer orderId,@Param("status") Integer status);
}