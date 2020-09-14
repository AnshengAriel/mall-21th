package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.mapper.SearchHistoryMapper;
import com.cskaoyan.mall.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    SearchHistoryMapper searchHistoryMapper;

    @Override
    public List<String> queryBykeyword(String keyword) {
        List<String> list = searchHistoryMapper.selectByKeyword("%" + keyword + "%");
        return list;
    }
}
