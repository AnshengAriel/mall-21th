package com.cskaoyan.mall.service.service_weixin_wangminyi.impl;

import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.FootprintForList;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.Wangminyi_Footprint;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.Wangminyi_FootprintExample;
import com.cskaoyan.mall.mapper.mapper_weixin_wangminyi.Wangminyi_FootprintMapper;
import com.cskaoyan.mall.service.service_weixin_wangminyi.Wangminyi_FootprintService;
import com.cskaoyan.mall.service.service_weixin_wangminyi.Wangminyi_GoodsService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Wangminyi_FootprintServiceImpl implements Wangminyi_FootprintService {
    @Autowired
    Wangminyi_FootprintMapper footprintMapper;
    @Autowired
    Wangminyi_GoodsService goodsService;


    @Override
    public List<Wangminyi_Footprint> queryAllFootprints(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        Wangminyi_FootprintExample footprintExample = new Wangminyi_FootprintExample();
        footprintExample.createCriteria().andIdIsNotNull();
        List<Wangminyi_Footprint> footprints = footprintMapper.selectByExample(footprintExample);
        if (footprints.size() == 0) {
            return null;
        }
        return footprints;
    }

    //把数据库冲查询的足迹转换为返回需要的格式
    @Override
    public List<FootprintForList> footprintToFootprintForListVo(List<Wangminyi_Footprint> footprints) {
        List<FootprintForList> footprintForLists = new ArrayList<>();
        for (Wangminyi_Footprint footprint : footprints) {
            FootprintForList footprintForList = new FootprintForList();
            footprintForList.setAddTime(new Date());
            footprintForList.setBrief(goodsService.queryBriefById(footprint.getGoodsId()));
            footprintForList.setGoodsId(footprint.getGoodsId());
            footprintForList.setId(footprint.getId());
            footprintForList.setName(goodsService.queryNameById(footprint.getGoodsId()));
            footprintForList.setPicUrl(goodsService.queryUrlById(footprint.getGoodsId()));
            footprintForList.setRetailPrice(goodsService.queryRetailPriceById(footprint.getGoodsId()));
            footprintForLists.add(footprintForList);
        }
        if (footprintForLists.size() == 0) {
            return null;
        }
        return footprintForLists;
    }

    @Override
    public void deleteFootprint(Integer footprintId) {
        footprintMapper.deleteByPrimaryKey(footprintId);
    }
}
