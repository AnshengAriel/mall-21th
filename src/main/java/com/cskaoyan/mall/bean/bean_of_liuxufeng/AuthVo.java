package com.cskaoyan.mall.bean.bean_of_liuxufeng;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthVo {
        private UserInfoBean userInfo;
        private String tokenExpire;
        private String token;

        public void setValue(){
            this.token="jf8h6zcgrs6z5rj8v9ixjso6i4n9puf1";
            this.tokenExpire="2020-07-01T17:34:37.225";
        }
}
