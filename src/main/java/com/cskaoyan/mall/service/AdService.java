package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.bean_of_liuxufeng.Ad;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.AdBo;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.AdVo;

public interface AdService {
//    AdVo queryAd(Integer page, Integer limit, String sort, String order); 弃用，更新为下面的

    AdVo queryAd(AdBo adBo);

    int updateAdById(Ad ad);

    int deleteAdById(Ad ad);

    Ad createAd(Ad ad);
}
