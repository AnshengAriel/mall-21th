package com.cskaoyan.mall.service.wx_brand_jiayi;

import com.cskaoyan.mall.bean.wx_brand_jiayi.BaseData9;
import com.cskaoyan.mall.bean.wx_brand_jiayi.Brand;
import com.cskaoyan.mall.bean.wx_brand_jiayi.BrandBean;

import java.util.List;

public interface BrandService9 {

    BaseData9 querytBrand(Integer page, Integer size);

    Brand queryBrandDetail(Integer id);
}
