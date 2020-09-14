package com.cskaoyan.mall.bean.bean_of_liuxufeng.enmu;

public enum CouponTypeEnum {
    Universal (0,"通用领券"),
    Registration (1,"注册赠券"),
    Redemption   (2,"兑换码");
    private Integer code;
    private String value;

    CouponTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
