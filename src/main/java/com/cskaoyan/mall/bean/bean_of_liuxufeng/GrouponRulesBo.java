package com.cskaoyan.mall.bean.bean_of_liuxufeng;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrouponRulesBo {
    Integer page;
    Integer limit;
    String sort;
    String order;
    Integer goodsId;
}
