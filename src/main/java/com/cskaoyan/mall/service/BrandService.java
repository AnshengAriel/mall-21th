package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.bean_of_wangyan.BaseData;
import com.cskaoyan.mall.bean.bean_of_wangyan.Brand;

public interface BrandService {
    BaseData queryBrandList(Integer page, Integer limit, String sort , String order, Integer id, String name);

    Brand createBrand(Brand brand);

    Brand updateBrand(Brand brand);

    void deleteBrand(Brand brand);
}
