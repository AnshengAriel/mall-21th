package com.cskaoyan.mall.service.service_weixin_wangminyi.impl;

import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.FeedBackBo;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.Wangminyi_Feedback;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.Wangminyi_FeedbackExample;
import com.cskaoyan.mall.mapper.mapper_weixin_wangminyi.Wangminyi_FeedbackMapper;
import com.cskaoyan.mall.service.WxGoodsService;
import com.cskaoyan.mall.service.service_weixin_wangminyi.Wangminyi_FeedbackService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class Wangminyi_FeedbackServiceImpl implements Wangminyi_FeedbackService {
    @Autowired
    Wangminyi_FeedbackMapper feedbackMapper;
    @Autowired
    WxGoodsService goodsService;



    @Override
    public void insertNewFeedback(FeedBackBo feedBackBo) {
        Subject subject = SecurityUtils.getSubject();
        String adminUsername = (String)subject.getPrincipals().getPrimaryPrincipal();
        //缺 status
        Wangminyi_Feedback feedBack = new Wangminyi_Feedback();

        feedBack.setId(null);
        feedBack.setUserId(goodsService.getUserId());
        feedBack.setUsername(adminUsername);
        feedBack.setMobile(feedBackBo.getMobile());
        feedBack.setFeedType(feedBackBo.getFeedType());
        feedBack.setContent(feedBackBo.getContent());
        feedBack.setStatus(null);
        feedBack.setHasPicture(feedBackBo.getHasPicture());
        if (feedBack.getHasPicture() == true) {
            feedBack.setPicUrls(feedBackBo.getPicUrls().get(0));
        }
        feedBack.setAddTime(new Date());
        feedBack.setUpdateTime(new Date());
        feedBack.setDeleted(false);
        feedbackMapper.insertSelective(feedBack);
    }
}
