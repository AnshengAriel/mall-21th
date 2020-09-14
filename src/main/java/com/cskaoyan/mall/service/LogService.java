package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.bean_of_hanyuandong.LogListVo;

public interface LogService {
    LogListVo queryLogs(Integer page,Integer limit,String sort,String order);

    LogListVo queryLogsByName(Integer page, Integer limit, String sort, String order, String name);
}
