package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.bean_of_liuxufeng.GrouponRulesBo;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.GrouponRulesVO;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.GrouponVo;

public interface GrouponRulesService {
    GrouponRulesVO queryGroupon(GrouponRulesBo groupon);
}
