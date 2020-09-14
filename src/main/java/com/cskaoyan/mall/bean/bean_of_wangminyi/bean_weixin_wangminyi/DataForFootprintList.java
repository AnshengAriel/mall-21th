package com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataForFootprintList<T> {
    List<T> footprintList;
    Integer totalPages;

}
