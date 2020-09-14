package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.bean_of_liuxufeng.*;
import com.cskaoyan.mall.mapper.GrouponRulesMapper;
import com.cskaoyan.mall.service.GrouponRulesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrouponRulesServiceImpl implements GrouponRulesService {
    @Autowired
    GrouponRulesMapper grouponRulesMapper;

    @Override
    public GrouponRulesVO queryGroupon(GrouponRulesBo groupon) {
        GrouponRulesExample grouponRulesExample = new GrouponRulesExample();
        grouponRulesExample.setOrderByClause(groupon.getSort() +" " + groupon.getOrder());
        if(groupon.getGoodsId()!=null)grouponRulesExample.createCriteria().andGoodsIdEqualTo(groupon.getGoodsId());
        PageHelper.startPage(groupon.getPage(),groupon.getLimit());
        List<GrouponRules> grouponRules = grouponRulesMapper.selectByExample(grouponRulesExample);
        PageInfo<GrouponRules> grouponRulesPageInfo = new PageInfo<>(grouponRules);
        long total = grouponRulesPageInfo.getTotal();
        GrouponRulesVO grouponRulesVO = new GrouponRulesVO(total, grouponRules);
        return grouponRulesVO;
    }
}
