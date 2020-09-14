package com.cskaoyan.mall.bean.bean_of_liuxufeng.enmu;

public enum CouponStatusEnum {
    NORMAL(0,"正常"),
    EXPIRED(1,"已过期"),
    REMOVED(2,"已下架");
    private Integer code;
    private String value;

    CouponStatusEnum(Integer code, String value) {
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
