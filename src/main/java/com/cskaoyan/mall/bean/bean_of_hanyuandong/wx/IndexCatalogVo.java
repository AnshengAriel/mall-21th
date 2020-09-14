package com.cskaoyan.mall.bean.bean_of_hanyuandong.wx;

import com.cskaoyan.mall.bean.bean_of_hanyuandong.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndexCatalogVo {
    List<Category> categoryList;
    Category currentCategory;
    List<Category> currentSubCategory;
}
