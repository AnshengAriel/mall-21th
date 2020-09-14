package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.bean_of_hanyuandong.wx.OrderGoods_Han;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.wx.OrderGoodsExample_Han;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderGoodsMapper_han {
    long countByExample(OrderGoodsExample_Han example);

    int deleteByExample(OrderGoodsExample_Han example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderGoods_Han record);

    int insertSelective(OrderGoods_Han record);

    List<OrderGoods_Han> selectByExample(OrderGoodsExample_Han example);

    OrderGoods_Han selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderGoods_Han record, @Param("example") OrderGoodsExample_Han example);

    int updateByExample(@Param("record") OrderGoods_Han record, @Param("example") OrderGoodsExample_Han example);

    int updateByPrimaryKeySelective(OrderGoods_Han record);

    int updateByPrimaryKey(OrderGoods_Han record);

    Integer selectOrderIdByOrderGoodsId(@Param("id") Integer orderGoodsId);

    void logicDeleteOrderGoodsByOrderId(@Param("orderId") Integer orderId);
}