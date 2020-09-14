package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.bean_of_tangronghua.CommentVo;
import com.cskaoyan.mall.bean.bean_of_wangminyi.BaseData;
import com.cskaoyan.mall.bean.bean_of_wangminyi.Comment;

public interface CommentService {
    BaseData queryComment(Integer page, Integer limit, String sort, String order);

    int deleteComment(Comment comment);

    CommentVo queryComment1(Integer page, Integer size, Integer valueId, Integer type, Integer showType);

    CommentVo queryComment2(Integer valueId, Integer type);
}


