package com.cskaoyan.mall.bean.bean_of_liuxufeng;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoBean {
    private String nickName;
    private String avatarUrl;

    public void setValue(){
        this.avatarUrl="https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80";
        this.nickName="test";
    }
}
