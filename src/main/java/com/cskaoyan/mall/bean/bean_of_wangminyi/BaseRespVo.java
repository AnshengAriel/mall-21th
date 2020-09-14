package com.cskaoyan.mall.bean.bean_of_wangminyi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseRespVo {
    Object data;
    String errmsg;
    Integer errno;

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
}
