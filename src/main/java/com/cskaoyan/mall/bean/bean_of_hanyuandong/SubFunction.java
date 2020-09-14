package com.cskaoyan.mall.bean.bean_of_hanyuandong;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubFunction {
    String id;
    String label;
    List<SystemPermission> children;
}
