package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.bean_of_liuxufeng.*;
import com.cskaoyan.mall.mapper.GrouponMapper;
import com.cskaoyan.mall.mapper.GrouponRulesMapper;
import com.cskaoyan.mall.service.GrouponService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrouponServiceImpl implements GrouponService {
    @Autowired
    GrouponMapper grouponMapper;

    @Override
    public GrouponVo queryGroupon(GrouponRulesBo grouponRulesBo) {
        /**
         * 需要调用别人的接口，具体参数请看Groupon_test
         */
        return null;
    }

    @Override
    public int updateGrouponById(Groupon groupon) {
        GrouponExample grouponExample = new GrouponExample();
        grouponExample.createCriteria().andIdEqualTo(groupon.getId());
        int i = grouponMapper.updateByExample(groupon, grouponExample);
        return i;
    }

    @Override
    public int deleteGrouponById(Groupon groupon) {
        GrouponExample grouponExample = new GrouponExample();
        grouponExample.createCriteria().andIdEqualTo(groupon.getId());
        int i = grouponMapper.deleteByExample(grouponExample);
        return i;
    }
}
