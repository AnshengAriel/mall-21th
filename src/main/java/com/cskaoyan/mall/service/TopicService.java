package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.bean_of_liuxufeng.Topic;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.TopicBo;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.TopicVo;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.TopicWithGoods;

import java.util.List;
import java.util.Map;

public interface TopicService {
    TopicVo queryTopic(TopicBo topicBo);

    int updateTopicById(Topic topic);

    int deleteTopicById(Topic topic);

    TopicWithGoods insertTopic(TopicWithGoods topic);

    Object queryTopics(Integer page, Integer size, Integer valueId, Integer type, Integer showType);

    List<Topic> queryRelatedTopics(Integer id);

    Map<String, Object> queryTopic2(Integer id);
}
