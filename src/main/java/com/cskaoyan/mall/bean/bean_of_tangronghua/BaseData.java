package com.cskaoyan.mall.bean.bean_of_tangronghua;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseData<T> {
    List<T> items;
    long total;
}
