package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.bean_of_hanyuandong.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper1 {
    long countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<CategoryListVo> selectList();

    List<CategoryL1Vo> selectCategoryL1Vo();

    void logicDeleteCategoryById(@Param("id") Integer id);
}