package com.cskaoyan.mall.bean.bean_of_liuxufeng;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseList {
    long total;
    List items;
}
