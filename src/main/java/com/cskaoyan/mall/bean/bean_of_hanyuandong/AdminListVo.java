package com.cskaoyan.mall.bean.bean_of_hanyuandong;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminListVo {
    Long total;
    List<AdminVo> items;
}
