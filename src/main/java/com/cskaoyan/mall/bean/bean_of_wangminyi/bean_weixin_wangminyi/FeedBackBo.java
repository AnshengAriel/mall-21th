package com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedBackBo {
    private String content;
    private String feedType;
    private Boolean hasPicture;
    private String mobile;
    private List<String> picUrls;

}
