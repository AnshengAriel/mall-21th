package com.cskaoyan.mall.bean.wx_brand_jiayi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseData9<T> {

    List<T> brandList;
    Long totalPages;
}
