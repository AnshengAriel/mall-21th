package com.cskaoyan.mall.bean.bean_of_tangronghua;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoVo {
    List<String> roles;
    String name;
    List<String> perms;
    String avatar;
}
