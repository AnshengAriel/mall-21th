package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.bean_of_tangronghua.CommentVo;
import com.cskaoyan.mall.bean.bean_of_wangminyi.*;
import com.cskaoyan.mall.mapper.CommentMapper;
import com.cskaoyan.mall.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;


    @Override
    public BaseData queryComment(Integer page, Integer limit, String sort, String order) {
        CommentExample commentExample = new CommentExample();
        commentExample.setOrderByClause(sort + " " + order);
        PageHelper.startPage(page, limit);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        long total = pageInfo.getTotal();
        return new BaseData(comments, total);
    }

    @Override
    public int deleteComment(Comment comment) {
        comment.setDeleted(true);
        return commentMapper.updateByPrimaryKey(comment);
    }
        /**list*/
    @Override
    public CommentVo queryComment1(Integer page, Integer size, Integer valueId, Integer type, Integer showType) {
        PageHelper.startPage(page, size);
        return null;
    }

    /**count*/
    @Override
    public CommentVo queryComment2(Integer valueId, Integer type) {

        return null;
    }
}
