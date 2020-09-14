package com.cskaoyan.mall.bean.wx_collect_jiayi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseData8<T> {

    List<T> collectList;
    Long totalPages;
}