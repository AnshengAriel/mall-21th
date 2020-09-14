package com.cskaoyan.mall.controller.controller_weixin_wangyan;


/**  TopicList: WxApiRoot + 'topic/list', //专题列表
 TopicDetail: WxApiRoot + 'topic/detail', //专题详情
 TopicRelated: WxApiRoot + 'topic/related', //相关专题*/

import com.cskaoyan.mall.bean.BaseRespVo;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.Topic;
import com.cskaoyan.mall.bean.bean_of_wangyan.WxData;
import com.cskaoyan.mall.service.impl.TopicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("wx/topic")
public class TopicController2 {
    @Autowired
    TopicServiceImpl topicService;

    @GetMapping("list")
    public BaseRespVo list2(Integer page, Integer size, Integer valueId, Integer type, Integer showType) {
        WxData wxData = (WxData) topicService.queryTopics(page, size, valueId, type, showType);
        return BaseRespVo.ok(wxData);
    }


    @GetMapping("detail")
    public BaseRespVo detail(Integer id) {
        Map<String, Object> map = topicService.queryTopic2(id);
        return BaseRespVo.ok(map);
    }

    @GetMapping("related")
    public BaseRespVo related(Integer id) {
        List<Topic> list = topicService.queryRelatedTopics(id);
        return BaseRespVo.ok(list.toArray());
    }
}