package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.bean_of_tangronghua.WxGoods;
import com.cskaoyan.mall.bean.bean_of_tangronghua.WxGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxGoodsMapper {
    long countByExample(WxGoodsExample example);

    int deleteByExample(WxGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WxGoods record);

    int insertSelective(WxGoods record);

    List<WxGoods> selectByExample(WxGoodsExample example);

    WxGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WxGoods record, @Param("example") WxGoodsExample example);

    int updateByExample(@Param("record") WxGoods record, @Param("example") WxGoodsExample example);

    int updateByPrimaryKeySelective(WxGoods record);

    int updateByPrimaryKey(WxGoods record);
}