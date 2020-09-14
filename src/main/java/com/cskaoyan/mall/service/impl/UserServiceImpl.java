package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.bean_of_tangronghua.*;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    CollectMapper collectMapper;

    @Autowired
    FoodprintMapper foodprintMapper;

    @Autowired
    Search_historyMapper search_historyMapper;

    @Autowired
    FeedbackMapper feedbackMapper;

    @Override
    public BaseData queryUsers(Integer page,Integer limit,String sort,String order,String username,String mobile) {
        //每次执行不同条件的sql语句，获得一个新的example对象
        UserExample userExample = new UserExample();
        //排序字符串拼接
        userExample.setOrderByClause(sort + " "+ order);//给example塞条件

        //在此增加其他条件
        //例如: userExample.createCriteria().andAgeBetween()
        UserExample.Criteria criteria = userExample.createCriteria();
        if (username != null){
            criteria.andUsernameLike("%" + username + "%");
        }
        if (mobile != null) {
            criteria.andMobileLike("%" + mobile + "%");
        }
        //执行查询之前使用分页
        PageHelper.startPage(page,limit);
        List<User> users = userMapper.selectByExample(userExample);//用这样的条件查询用户
        //将users这个list封装到 PageInfo对象中 自动分页
        PageInfo<User> pageInfo = new PageInfo<>(users);
        //获取总的记录数
        long total = pageInfo.getTotal();
        return new BaseData(users,total);
    }

    @Override
    public BaseData queryAddresses(Integer page, Integer limit, String sort, String order, Integer userId, String name) {
        AddressExample addressExample = new AddressExample();
        addressExample.setOrderByClause(sort + " " + order);

        AddressExample.Criteria criteria = addressExample.createCriteria();
        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (name != null) {
            criteria.andNameEqualTo(name);
        }

        PageHelper.startPage(page,limit);
        List<Address> addresses = addressMapper.selectByExample(addressExample);
        PageInfo<Address> pageInfo = new PageInfo<>(addresses);
        long total = pageInfo.getTotal();
        return new BaseData(addresses,total);
    }

    @Override
    public BaseData queryCollects(Integer page, Integer limit, String sort, String order, Integer userId, Integer valueId) {
        CollectExample collectExample = new CollectExample();
        collectExample.setOrderByClause(sort + " " + order);

        CollectExample.Criteria criteria = collectExample.createCriteria();
        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (valueId != null) {
            criteria.andValueIdEqualTo(valueId);
        }

        PageHelper.startPage(page,limit);
        List<Collect> collects = collectMapper.selectByExample(collectExample);
        PageInfo<Collect> pageInfo = new PageInfo<>(collects);
        long total = pageInfo.getTotal();
        return new BaseData(collects,total);
    }

    @Override
    public BaseData queryFoodprints(Integer page, Integer limit, String sort, String order, Integer userId, Integer goodsId) {
        FoodprintExample foodprintExample = new FoodprintExample();
        foodprintExample.setOrderByClause(sort + " " + order);

        FoodprintExample.Criteria criteria = foodprintExample.createCriteria();
        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (goodsId != null) {
            criteria.andGoodsIdEqualTo(goodsId);
        }
        PageHelper.startPage(page,limit);
        List<Foodprint> foodprints = foodprintMapper.selectByExample(foodprintExample);
        PageInfo<Foodprint> pageInfo = new PageInfo<>(foodprints);
        long total = pageInfo.getTotal();
        return new BaseData(foodprints,total);
    }

    @Override
    public BaseData queryHistory(Integer page, Integer limit, String sort, String order, Integer userId, String keyword) {
        Search_historyExample search_historyExample = new Search_historyExample();
        search_historyExample.setOrderByClause(sort + " " + order);

        Search_historyExample.Criteria criteria = search_historyExample.createCriteria();
        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (keyword != null) {
            criteria.andKeywordLike("%" + keyword + "%");
        }

        PageHelper.startPage(page,limit);
        List<Search_history> search_histories = search_historyMapper.selectByExample(search_historyExample);
        PageInfo<Search_history> pageInfo = new PageInfo<>(search_histories);
        long total = pageInfo.getTotal();
        return new BaseData(search_histories,total);
    }

    @Override
    public BaseData queryFeedback(Integer page, Integer limit, String sort, String order, Integer id, String username) {
        FeedbackExample feedbackExample = new FeedbackExample();
        feedbackExample.setOrderByClause(sort + " " + order);

        FeedbackExample.Criteria criteria = feedbackExample.createCriteria();
        if (username != null) {
            criteria.andUsernameLike("%" + username + "%");
        }
        if (id != null) {
            criteria.andIdEqualTo(id);
        }

        PageHelper.startPage(page,limit);
        List<Feedback> feedbacks = feedbackMapper.selectByExample(feedbackExample);
        PageInfo<Feedback> pageInfo = new PageInfo<>(feedbacks);
        long total = pageInfo.getTotal();
        return new BaseData(feedbacks,total);
    }
}
