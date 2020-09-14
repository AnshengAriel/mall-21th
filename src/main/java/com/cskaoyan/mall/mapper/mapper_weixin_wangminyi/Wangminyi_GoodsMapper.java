package com.cskaoyan.mall.mapper.mapper_weixin_wangminyi;

import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.Wangminyi_Goods;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.Wangminyi_GoodsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Wangminyi_GoodsMapper {
    long countByExample(Wangminyi_GoodsExample example);

    int deleteByExample(Wangminyi_GoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Wangminyi_Goods record);

    int insertSelective(Wangminyi_Goods record);

    List<Wangminyi_Goods> selectByExampleWithBLOBs(Wangminyi_GoodsExample example);

    List<Wangminyi_Goods> selectByExample(Wangminyi_GoodsExample example);

    Wangminyi_Goods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Wangminyi_Goods record, @Param("example") Wangminyi_GoodsExample example);

    int updateByExampleWithBLOBs(@Param("record") Wangminyi_Goods record, @Param("example") Wangminyi_GoodsExample example);

    int updateByExample(@Param("record") Wangminyi_Goods record, @Param("example") Wangminyi_GoodsExample example);

    int updateByPrimaryKeySelective(Wangminyi_Goods record);

    int updateByPrimaryKeyWithBLOBs(Wangminyi_Goods record);

    int updateByPrimaryKey(Wangminyi_Goods record);
}