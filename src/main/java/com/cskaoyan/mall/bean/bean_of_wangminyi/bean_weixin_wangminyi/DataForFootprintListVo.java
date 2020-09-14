package com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataForFootprintListVo {
    Object data;
    String errmsg;
    Integer errno;

    public static DataForFootprintListVo ok() {
        DataForFootprintListVo baseRespVo = new DataForFootprintListVo();
        baseRespVo.setErrmsg("成功");
        baseRespVo.setErrno(0);
        return baseRespVo;
    }
    public static DataForFootprintListVo ok(Object data) {
        DataForFootprintListVo baseRespVo = ok();
        baseRespVo.setData(data);
        return baseRespVo;
    }
}
