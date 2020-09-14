package com.cskaoyan.mall.controller.controller_weixin_wangyan;

import com.cskaoyan.mall.bean.BaseRespVo;
import com.cskaoyan.mall.bean.bean_of_tangronghua.CommentVo;
import com.cskaoyan.mall.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**  CommentList: WxApiRoot + 'comment/list', //评论列表
     CommentCount: WxApiRoot + 'comment/count', //评论总数
     CommentPost: WxApiRoot + 'comment/post', //发表评论
 */
@RestController
@RequestMapping("wx/comment")
public class CommentController2 {
    @Autowired
    CommentServiceImpl commentService;

    @GetMapping("list")
    public BaseRespVo list(Integer page, Integer size, Integer valueId, Integer type, Integer showType) {
        CommentVo commentVo = commentService.queryComment1(page, size, valueId, type, showType);
        return BaseRespVo.ok(commentVo);
    }
    @GetMapping("count")
    public BaseRespVo count( Integer valueId, Integer type) {
        CommentVo commentVo = commentService.queryComment2(valueId, type);
        return BaseRespVo.ok(commentVo);
    }
}
