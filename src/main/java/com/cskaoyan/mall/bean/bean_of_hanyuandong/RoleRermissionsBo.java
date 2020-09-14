package com.cskaoyan.mall.bean.bean_of_hanyuandong;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRermissionsBo {
    List<String> permissions;
    Integer roleId;
}
