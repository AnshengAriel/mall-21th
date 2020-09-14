package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseRespVo;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.Log;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.LogListVo;
import com.cskaoyan.mall.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LogController {

    @Autowired
    LogService logService;

    @RequestMapping("/admin/log/list")
    public BaseRespVo list(Integer page,Integer limit,String sort,String order) {
        LogListVo logListVo = logService.queryLogs(page,limit,sort,order);
        return BaseRespVo.ok(logListVo);
    }

    @RequestMapping(value = "/admin/log/list",params = "name")
    public BaseRespVo search(Integer page,Integer limit,String sort,String order,String name) {
        LogListVo logListVo = logService.queryLogsByName(page,limit,sort,order,name);
        return BaseRespVo.ok(logListVo);
    }
}
