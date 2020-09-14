package com.cskaoyan.mall.bean.bean_of_wangyan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxData<T> {
    List<T> data;
    long count;
}
