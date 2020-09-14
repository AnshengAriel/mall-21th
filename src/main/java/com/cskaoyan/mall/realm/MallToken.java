package com.cskaoyan.mall.realm;

import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

@Data
public class MallToken extends UsernamePasswordToken {
    String type;

    public MallToken(String username, String password, String type) {
        super(username, password);
        this.type = type;
    }
}
