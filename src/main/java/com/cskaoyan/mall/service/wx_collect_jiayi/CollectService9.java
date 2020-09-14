package com.cskaoyan.mall.service.wx_collect_jiayi;

import com.cskaoyan.mall.bean.wx_collect_jiayi.BaseData8;
import com.cskaoyan.mall.bean.wx_collect_jiayi.CollectBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectService9 {

    BaseData8 queryCollect(Integer type, Integer userId, Integer page, Integer size);

    Integer queryUserId(String username);

    Integer queryId(Integer type,Integer valueId,Integer userId);

    Integer insertCollect(Integer userId,Integer valueId,Integer type);

    Integer deleteCollect(Integer userId,Integer valueId,Integer type);
}
