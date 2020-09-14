package com.cskaoyan.mall.service;


import com.cskaoyan.mall.bean.bean_of_tangronghua.BaseData;

public interface UserService {
    BaseData queryUsers(Integer page,Integer limit,String sort,String order,String username,String mobile);
    BaseData queryAddresses(Integer page, Integer limit, String sort, String order, Integer userId, String name);
    BaseData queryCollects(Integer page, Integer limit, String sort, String order, Integer userId, Integer valueId);
    BaseData queryFoodprints(Integer page, Integer limit, String sort, String order, Integer userId, Integer goodsId);
    BaseData queryHistory(Integer page, Integer limit, String sort, String order, Integer userId, String keyword);
    BaseData queryFeedback(Integer page, Integer limit, String sort, String order, Integer id, String username);
}
