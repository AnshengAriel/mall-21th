package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.bean_of_liuxufeng.Groupon;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.GrouponRulesBo;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.GrouponRulesVO;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.GrouponVo;

public interface GrouponService {
    GrouponVo queryGroupon(GrouponRulesBo grouponRulesBo);

    int updateGrouponById(Groupon groupon);

    int deleteGrouponById(Groupon groupon);
}
