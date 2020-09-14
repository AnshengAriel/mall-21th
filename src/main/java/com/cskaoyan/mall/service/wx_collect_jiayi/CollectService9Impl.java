package com.cskaoyan.mall.service.wx_collect_jiayi;


import com.cskaoyan.mall.bean.wx_collect_jiayi.BaseData8;
import com.cskaoyan.mall.bean.wx_collect_jiayi.CollectBean;
import com.cskaoyan.mall.mapper.wx_collect_jiayi.CollectMapper9;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CollectService9Impl implements  CollectService9{


    @Autowired
    CollectMapper9 collectMapper9;


    @Override
    public BaseData8 queryCollect(Integer type, Integer userId, Integer page, Integer size) {

        PageHelper.startPage(page,size);
        List<CollectBean> collectBeans = collectMapper9.selectCollect(type, userId);
        PageInfo<CollectBean> collectBeanPageInfo = new PageInfo<>(collectBeans);
        long totalPages = collectBeanPageInfo.getTotal();
        totalPages=(totalPages/size)+1;
        return new BaseData8(collectBeans,totalPages);

    }

    @Override
    public Integer queryUserId(String username) {

        return collectMapper9.selectUserId(username);
    }

    @Override
    public Integer queryId(Integer type,Integer valueId,Integer userId) {
        return collectMapper9.selectId(type, valueId,userId);
    }

    @Override
    public Integer insertCollect(Integer userId, Integer valueId, Integer type) {
        return collectMapper9.insertCollect(userId, valueId, type);
    }

    @Override
    public Integer deleteCollect(Integer userId, Integer valueId, Integer type) {
        return collectMapper9.deleteCollect(userId, valueId, type);
    }
}
