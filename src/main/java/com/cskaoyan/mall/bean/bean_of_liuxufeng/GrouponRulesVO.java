package com.cskaoyan.mall.bean.bean_of_liuxufeng;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrouponRulesVO {
    private long total;
    private List<GrouponRules> items;
}
