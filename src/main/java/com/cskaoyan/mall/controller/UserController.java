package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.bean_of_tangronghua.BaseData;
import com.cskaoyan.mall.bean.bean_of_tangronghua.BaseRespVo;
import com.cskaoyan.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("admin/user/list")
    public BaseRespVo listUser(Integer page,Integer limit,String sort,String order,String username,String mobile){
        //当前页码，一页显示多少条数据，排序order by add_time desc
        BaseData baseData = userService.queryUsers(page, limit, sort, order,username,mobile);
        return BaseRespVo.ok(baseData);
    }

    @RequestMapping("admin/address/list")
    public BaseRespVo listAddress(Integer page, Integer limit, String sort, String order, Integer userId, String name){
        BaseData baseData = userService.queryAddresses(page, limit, sort, order,userId,name);
        return BaseRespVo.ok(baseData);
    }

    @RequestMapping("admin/collect/list")
    public BaseRespVo listCollect(Integer page, Integer limit, String sort, String order, Integer userId, Integer valueId){
        BaseData baseData = userService.queryCollects(page, limit, sort, order, userId, valueId);
        return BaseRespVo.ok(baseData);
    }

    @RequestMapping("admin/footprint/list")
    public BaseRespVo listFoodprint(Integer page, Integer limit, String sort, String order, Integer userId, Integer goodsId){
        BaseData baseData = userService.queryFoodprints(page, limit, sort, order, userId, goodsId);
        return BaseRespVo.ok(baseData);
    }

    @RequestMapping("admin/history/list")
    public BaseRespVo listHistory(Integer page, Integer limit, String sort, String order, Integer userId, String keyword){
        BaseData baseData = userService.queryHistory(page, limit, sort, order, userId, keyword);
        return BaseRespVo.ok(baseData);
    }

    @RequestMapping("admin/feedback/list")
    public BaseRespVo listFeedback(Integer page, Integer limit, String sort, String order, Integer id, String username){
        BaseData baseData = userService.queryFeedback(page, limit, sort, order, id, username);
        return BaseRespVo.ok(baseData);
    }
}
