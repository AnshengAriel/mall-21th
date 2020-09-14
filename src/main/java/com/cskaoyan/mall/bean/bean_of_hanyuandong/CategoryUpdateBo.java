package com.cskaoyan.mall.bean.bean_of_hanyuandong;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class CategoryUpdateBo {
    private Integer id;
    private String name;
    private String keywords;
    private String desc;
    private String iconUrl;
    private String picUrl;
    private String level;
    private List<CategoryVo> children;
    private Integer pid;
}
