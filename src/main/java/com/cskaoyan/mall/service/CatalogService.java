package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.bean_of_hanyuandong.wx.CurrentCatalogVo;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.wx.IndexCatalogVo;

public interface CatalogService {

    CurrentCatalogVo queryCategoryById(Integer id);

    IndexCatalogVo queryCategory();
}
