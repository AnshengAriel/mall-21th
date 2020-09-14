package com.cskaoyan.mall.bean.bean_of_hanyuandong.wx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentBo {

    String content;
    Boolean hasPicture;
    Integer orderGoodsId;
    List<String> picUrls;
    Integer star;
}
