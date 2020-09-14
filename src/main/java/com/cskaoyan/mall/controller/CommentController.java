package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.bean_of_wangminyi.BaseRespVo;
import com.cskaoyan.mall.bean.bean_of_wangminyi.BaseData;
import com.cskaoyan.mall.bean.bean_of_wangminyi.Comment;
import com.cskaoyan.mall.bean.bean_of_wangminyi.Goods;
import com.cskaoyan.mall.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/comment")
public class CommentController {
    @Autowired
    CommentService commentService;


    //评论列表，加载图片失败了
    @RequestMapping("list")
    public BaseRespVo list(Integer page, Integer limit, String sort, String order) {
        BaseData baseData = commentService.queryComment(page, limit, sort, order);
        return BaseRespVo.ok(baseData);
    }

    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody Comment comment) {
        BaseRespVo baseRespVo = new BaseRespVo();
        int row = commentService.deleteComment(comment);
        if (row == 1) {
            baseRespVo.setErrno(0);
            baseRespVo.setErrmsg("成功");
        } else {
            baseRespVo.setErrno(500);
            baseRespVo.setErrmsg("失败");
        }
        return baseRespVo;
    }
}
