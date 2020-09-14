package com.cskaoyan.mall.bean.bean_of_wangminyi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

/*
* response preview:
* data: {total: 241,…}
items: [{id: 1181019, goodsSn: "11452", name: "grevious lady", categoryId: 1025000, brandId: 1001002,…},…]
total: 241
*/
public class BaseData<T> {
    List<T> items;
    long total;
}
