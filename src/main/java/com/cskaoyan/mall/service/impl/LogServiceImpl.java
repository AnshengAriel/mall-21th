package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.bean_of_hanyuandong.Log;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.LogExample;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.LogListVo;
import com.cskaoyan.mall.mapper.LogMapper;
import com.cskaoyan.mall.service.LogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogMapper logMapper;

    @Override
    public LogListVo queryLogs(Integer page,Integer limit,String sort,String order) {
        //查询之前先分页
        PageHelper.startPage(page,limit);

        List<Log> logList = logMapper.selectSortLog(sort,order,null);
        PageInfo<Log> pageInfo = new PageInfo<Log>(logList);
        long total = pageInfo.getTotal();
        LogListVo logListVo = new LogListVo();
        logListVo.setItems(logList);
        logListVo.setTotal(total);
        return logListVo;
    }

    @Override
    public LogListVo queryLogsByName(Integer page, Integer limit, String sort, String order, String name) {
        //查询之前先分页
        PageHelper.startPage(page,limit);
        List<Log> logList = logMapper.selectSortLog(sort,order,name);
        PageInfo<Log> pageInfo = new PageInfo<Log>(logList);
        long total = pageInfo.getTotal();
        LogListVo logListVo = new LogListVo();
        logListVo.setItems(logList);
        logListVo.setTotal(total);
        return logListVo;

    }
}
