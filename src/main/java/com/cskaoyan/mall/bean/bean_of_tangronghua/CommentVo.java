package com.cskaoyan.mall.bean.bean_of_tangronghua;

import com.cskaoyan.mall.bean.bean_of_wangminyi.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo {
    List<Comment> data;
    Integer count;
}
