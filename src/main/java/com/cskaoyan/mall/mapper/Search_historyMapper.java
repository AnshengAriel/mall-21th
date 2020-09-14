package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.bean_of_tangronghua.Search_history;
import com.cskaoyan.mall.bean.bean_of_tangronghua.Search_historyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Search_historyMapper {
    long countByExample(Search_historyExample example);

    int deleteByExample(Search_historyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Search_history record);

    int insertSelective(Search_history record);

    List<Search_history> selectByExample(Search_historyExample example);

    Search_history selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Search_history record, @Param("example") Search_historyExample example);

    int updateByExample(@Param("record") Search_history record, @Param("example") Search_historyExample example);

    int updateByPrimaryKeySelective(Search_history record);

    int updateByPrimaryKey(Search_history record);
}