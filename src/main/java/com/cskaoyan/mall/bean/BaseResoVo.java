package com.cskaoyan.mall.bean;

import lombok.Data;

@Data
public class BaseResoVo<T> {

    T data;
    String errmsg;
    Integer errno;

    public static BaseResoVo ok(){
        BaseResoVo baseResoVo = new BaseResoVo();
        baseResoVo.setErrmsg("成功");
        baseResoVo.setErrno(0);
        return baseResoVo;
    }

    public static BaseResoVo ok(Object data){
        BaseResoVo baseResoVo = BaseResoVo.ok();
        baseResoVo.setData(data);
        return baseResoVo;
    }

    public static BaseResoVo error(Integer errno, String errmsg){
        BaseResoVo baseResoVo = new BaseResoVo();
        baseResoVo.setErrmsg(errmsg);
        baseResoVo.setErrno(errno);
        return baseResoVo;
    }
}
