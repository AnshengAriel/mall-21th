package com.cskaoyan.mall.bean.bean_of_hanyuandong;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AdminCreateBo {
    String username;
    String password;
    String avatar;
    List<Integer> roleIds;
}
