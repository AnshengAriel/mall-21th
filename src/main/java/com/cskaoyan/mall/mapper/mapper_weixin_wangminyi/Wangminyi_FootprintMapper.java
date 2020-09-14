package com.cskaoyan.mall.mapper.mapper_weixin_wangminyi;

import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.Wangminyi_Footprint;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.Wangminyi_FootprintExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Wangminyi_FootprintMapper {
    long countByExample(Wangminyi_FootprintExample example);

    int deleteByExample(Wangminyi_FootprintExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Wangminyi_Footprint record);

    int insertSelective(Wangminyi_Footprint record);

    List<Wangminyi_Footprint> selectByExample(Wangminyi_FootprintExample example);

    Wangminyi_Footprint selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Wangminyi_Footprint record, @Param("example") Wangminyi_FootprintExample example);

    int updateByExample(@Param("record") Wangminyi_Footprint record, @Param("example") Wangminyi_FootprintExample example);

    int updateByPrimaryKeySelective(Wangminyi_Footprint record);

    int updateByPrimaryKey(Wangminyi_Footprint record);
}