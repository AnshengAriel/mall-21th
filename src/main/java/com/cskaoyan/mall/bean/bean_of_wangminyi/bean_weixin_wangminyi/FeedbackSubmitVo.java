package com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi;

import com.cskaoyan.mall.bean.BaseRespVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackSubmitVo {
    String errmsg;
    Integer errno;
    public static FeedbackSubmitVo ok() {
        FeedbackSubmitVo feedbackSubmitVo = new FeedbackSubmitVo();
        feedbackSubmitVo.setErrmsg("成功");
        feedbackSubmitVo.setErrno(0);
        return feedbackSubmitVo;
    }
}
