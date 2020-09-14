package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.bean_of_liuxufeng.*;
import com.cskaoyan.mall.bean.bean_of_wangminyi.Goods;
import com.cskaoyan.mall.bean.bean_of_wangyan.WxData;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.TopicMapper;
import com.cskaoyan.mall.service.TopicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    TopicMapper topicMapper;
    private GoodsMapper goodsMapper;

    @Override

    public TopicVo queryTopic(TopicBo topicBo) {
        TopicExample topicExample = new TopicExample();
        topicExample.setOrderByClause(topicBo.getSort()+" "+topicBo.getOrder());
        if(topicBo.getTitle()==null)topicBo.setTitle("");
        if (topicBo.getSubtitle()==null)topicBo.setSubtitle("");
        topicExample.createCriteria().andTitleLike("%"+topicBo.getTitle()+"%").andSubtitleLike("%"+topicBo.getSubtitle()+"%");
        PageHelper.startPage(topicBo.getPage(),topicBo.getLimit());
        List<Topic> topics = topicMapper.selectByExample(topicExample);
        PageInfo<Topic> topicPageInfo = new PageInfo<>(topics);
        long total = topicPageInfo.getTotal();
        TopicVo topicVo = new TopicVo(total, topics);
        return topicVo;
    }

    @Override
    public int updateTopicById(Topic topic) {
        TopicExample topicExample = new TopicExample();
        topicExample.createCriteria().andIdEqualTo(topic.getId());
        int i = topicMapper.updateByExample(topic,topicExample);
        return i;
    }

    @Override
    public int deleteTopicById(Topic topic) {
        TopicExample topicExample = new TopicExample();
        topicExample.createCriteria().andIdEqualTo(topic.getId());
        int i = topicMapper.deleteByExample(topicExample);
        return i;
    }

    @Override
    public TopicWithGoods insertTopic(TopicWithGoods topic) {
        /*
        TopicWithGoods 和 Topic 之间相互转换
         */
        Topic topic1 = new Topic(topic);
        topicMapper.insertSelective(topic1);
        topic.setId(topic1.getId());
        return topic;
    }

    @Override
    public Object queryTopics(Integer page, Integer size, Integer valueId, Integer type, Integer showType) {
    TopicExample topicExample = new TopicExample();
        PageHelper.startPage(page,size);
    List<Topic> topics = topicMapper.selectByExampleWithBLOBs(topicExample);
    PageInfo<Topic> pageInfo = new PageInfo<>(topics);
    long count = pageInfo.getTotal();
        return new WxData(topics, count);
    }

    @Override
    public List<Topic> queryRelatedTopics(Integer id) {
        ArrayList<Topic> list = new ArrayList<>();
        Topic currentTopic = topicMapper.selectByPrimaryKey(id);
        String title = currentTopic.getTitle();
        for (int i = 0; i < title.length() - 3; i++) {
            for (int j = 3; j > 0; j--) {
                String keywords = title.substring(i, i + j);
                TopicExample topicExample = new TopicExample();
                topicExample.setDistinct(true);
                topicExample.createCriteria().andTitleLike("%" + keywords + "%").
                        andIdNotEqualTo(currentTopic.getId());
                List<Topic> topics = topicMapper.selectByExampleWithBLOBs(topicExample);
                list.addAll(topics);
            }
        }
        ArrayList<Topic> result = new ArrayList<>();
        for (Topic topic : list) {
            boolean flag = true;
            for (Topic topic1 : result) {
                if (topic.getId().equals(topic1.getId())) {
                    flag = false;
                    break;
                }
            }
            if (flag) result.add(topic);
        }
        if (result.size() > 4) return result.subList(0, 4);
        return list;
    }

    @Override
    public Map<String, Object> queryTopic2(Integer id) {
        Topic topic = topicMapper.selectByPrimaryKey(id);
<<<<<<< HEAD

//        String[] goods = {topic.getGoods()};
        String[] goods = new String[]{};

=======
        String[] goods = new String[]{};
>>>>>>> 65ef7100fc5fa1bcab415ff12b49b1eb9a15238e
        HashMap<String, Object> map = new HashMap<>();
        map.put("topic", topic);
        ArrayList<Goods> list = new ArrayList<>();
        for (String good : goods) {
            int goodsId = Integer.parseInt(good);
            Goods goods2 = goodsMapper.selectByPrimaryKey(goodsId);
            list.add(goods2);
        }
        map.put("goods", list.toArray());
        return map;
    }
}
