package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.bean_of_hanyuandong.wx.CommentH;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.wx.CommentHExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentHMapper {
    long countByExample(CommentHExample example);

    int deleteByExample(CommentHExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CommentH record);

    int insertSelective(CommentH record);

    List<CommentH> selectByExample(CommentHExample example);

    CommentH selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommentH record, @Param("example") CommentHExample example);

    int updateByExample(@Param("record") CommentH record, @Param("example") CommentHExample example);

    int updateByPrimaryKeySelective(CommentH record);

    int updateByPrimaryKey(CommentH record);
}