package com.cskaoyan.mall.bean.bean_of_liuxufeng;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseRespVo {
    Integer errno;
    Object data;
    String errmsg;


    public static BaseRespVo ok() {
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrmsg("成功");
        baseRespVo.setErrno(0);
        return baseRespVo;
    }
    public static BaseRespVo ok(Object data) {
        BaseRespVo baseRespVo = ok();
        baseRespVo.setData(data);
        return baseRespVo;
    }

    public static BaseRespVo error() {
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrmsg("失败");
        baseRespVo.setErrno(10000);
        return baseRespVo;
    }
}
