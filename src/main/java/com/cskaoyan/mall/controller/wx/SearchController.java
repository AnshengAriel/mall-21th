package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.bean.BaseRespVo;
import com.cskaoyan.mall.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("wx/search/")
public class SearchController {

    @Autowired
    SearchService searchService;

    @RequestMapping("helper")
    public BaseRespVo helper(String keyword) {
        List<String> list = searchService.queryBykeyword(keyword);
        return BaseRespVo.ok(list);
    }

}
