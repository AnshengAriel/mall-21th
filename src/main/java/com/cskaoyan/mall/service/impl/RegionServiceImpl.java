package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.bean_of_wangyan.Region;
import com.cskaoyan.mall.bean.bean_of_wangyan.RegionExample;
import com.cskaoyan.mall.bean.bean_of_wangyan.RegionVo;
import com.cskaoyan.mall.bean.bean_of_wangyan.WxData;
import com.cskaoyan.mall.mapper.RegionMapper;
import com.cskaoyan.mall.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    RegionMapper regionMapper;

    @Override
    public RegionVo queryList() {
        RegionExample regionExample = new RegionExample();
        List<Region> regions = regionMapper.selectByExample(regionExample);
        RegionVo regionVo = new RegionVo(regions);
        return regionVo;
    }
}
