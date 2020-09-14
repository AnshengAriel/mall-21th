package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.bean_of_tangronghua.Foodprint;
import com.cskaoyan.mall.bean.bean_of_tangronghua.FoodprintExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FoodprintMapper {
    long countByExample(FoodprintExample example);

    int deleteByExample(FoodprintExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Foodprint record);

    int insertSelective(Foodprint record);

    List<Foodprint> selectByExample(FoodprintExample example);

    Foodprint selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Foodprint record, @Param("example") FoodprintExample example);

    int updateByExample(@Param("record") Foodprint record, @Param("example") FoodprintExample example);

    int updateByPrimaryKeySelective(Foodprint record);

    int updateByPrimaryKey(Foodprint record);
}