package com.cskaoyan.mall.bean.bean_of_wangminyi;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVoList {
    private Integer value;

    private String label;

    private List<CategoryChildrenVoList> children;
}
