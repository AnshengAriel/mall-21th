package com.cskaoyan.mall.controller.controller_weixin_wangminyi;

import com.cskaoyan.mall.bean.BaseRespVo;
import com.cskaoyan.mall.bean.bean_of_tangronghua.Feedback;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.FeedBackBo;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.FeedbackSubmitVo;
import com.cskaoyan.mall.service.service_weixin_wangminyi.Wangminyi_FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//反馈
@RestController
@RequestMapping("wx/feedback/")
public class FeedbackController {


    @Autowired
    Wangminyi_FeedbackService feedbackService;

    @RequestMapping("submit")
    public FeedbackSubmitVo submit(@RequestBody FeedBackBo feedBackBo){
        feedbackService.insertNewFeedback(feedBackBo);
        FeedbackSubmitVo feedbackSubmitVo = new FeedbackSubmitVo();
        return feedbackSubmitVo.ok();
    }

}
