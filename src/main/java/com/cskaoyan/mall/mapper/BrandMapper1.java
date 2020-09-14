package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.bean_of_wangyan.Brand;
import com.cskaoyan.mall.bean.bean_of_wangyan.BrandExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandMapper1 {
    long countByExample(BrandExample example);

    int deleteByExample(BrandExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Brand record);

    int insertSelective(Brand record);

    List<Brand> selectByExample(BrandExample example);

    Brand selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Brand record, @Param("example") BrandExample example);

    int updateByExample(@Param("record") Brand record, @Param("example") BrandExample example);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);
}