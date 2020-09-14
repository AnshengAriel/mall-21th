package com.cskaoyan.mall.bean;

import lombok.Data;


@Data
public class BaseResponseVo<T> {
    T data;
    String errno;
    String errmsg;

    public static BaseResponseVo ok() {
        BaseResponseVo responseVo = new BaseResponseVo<>();
        responseVo.setErrmsg("成功");
        responseVo.setErrno("0");
        return responseVo;
    }
    public static BaseResponseVo ok(Object data) {
        BaseResponseVo ok = ok();
        ok.setData(data);
        return ok;
    }
}
