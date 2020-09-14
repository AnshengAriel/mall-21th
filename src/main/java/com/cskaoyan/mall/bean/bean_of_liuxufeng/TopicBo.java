package com.cskaoyan.mall.bean.bean_of_liuxufeng;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicBo {
    Integer page;
    Integer limit;
    String sort;
    String order;
    String title;
    String subtitle;
}
