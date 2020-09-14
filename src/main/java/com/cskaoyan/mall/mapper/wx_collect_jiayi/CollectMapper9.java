package com.cskaoyan.mall.mapper.wx_collect_jiayi;


import com.cskaoyan.mall.bean.wx_collect_jiayi.CollectBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectMapper9 {

    List<CollectBean> selectCollect(@Param("type") Integer type, @Param("userId") Integer userId);

    Integer selectUserId(@Param("username") String username);

    Integer selectId(@Param("type") Integer type, @Param("valueId") Integer valueId, @Param("userId") Integer userId);

    Integer insertCollect(@Param("userId") Integer userId, @Param("valueId") Integer valueId, @Param("type") Integer type);

    Integer deleteCollect(@Param("userId") Integer userId, @Param("valueId") Integer valueId, @Param("type") Integer type);
}
