package com.cskaoyan.mall.mapper.wx_brand_jiayi;

import com.cskaoyan.mall.bean.wx_brand_jiayi.Brand;
import com.cskaoyan.mall.bean.wx_brand_jiayi.BrandBean;

import java.util.List;

public interface BrandMapper9 {

    List<BrandBean> selectBrand();

    Brand selectBrandDetail(Integer id);
}
