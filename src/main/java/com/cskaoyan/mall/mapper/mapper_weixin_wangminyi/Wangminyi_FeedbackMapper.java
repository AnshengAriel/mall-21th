package com.cskaoyan.mall.mapper.mapper_weixin_wangminyi;

import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.Wangminyi_Feedback;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.Wangminyi_FeedbackExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Wangminyi_FeedbackMapper {
    long countByExample(Wangminyi_FeedbackExample example);

    int deleteByExample(Wangminyi_FeedbackExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Wangminyi_Feedback record);

    int insertSelective(Wangminyi_Feedback record);

    List<Wangminyi_Feedback> selectByExample(Wangminyi_FeedbackExample example);

    Wangminyi_Feedback selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Wangminyi_Feedback record, @Param("example") Wangminyi_FeedbackExample example);

    int updateByExample(@Param("record") Wangminyi_Feedback record, @Param("example") Wangminyi_FeedbackExample example);

    int updateByPrimaryKeySelective(Wangminyi_Feedback record);

    int updateByPrimaryKey(Wangminyi_Feedback record);
}