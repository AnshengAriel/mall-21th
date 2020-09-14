package com.cskaoyan.mall.service.wx_brand_jiayi;

import com.cskaoyan.mall.bean.wx_brand_jiayi.BaseData9;
import com.cskaoyan.mall.bean.wx_brand_jiayi.Brand;
import com.cskaoyan.mall.bean.wx_brand_jiayi.BrandBean;
import com.cskaoyan.mall.mapper.wx_brand_jiayi.BrandMapper9;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@Transactional
public class BrandService9Impl implements  BrandService9 {


   @Autowired
    BrandMapper9 brandMapper9;


    @Override
    public BaseData9 querytBrand(Integer page, Integer size) {

        PageHelper.startPage(page,size);
        List<BrandBean> brandBeans = brandMapper9.selectBrand();
        PageInfo<BrandBean> brandBeanPageInfo = new PageInfo<>(brandBeans);
        long totalPages = brandBeanPageInfo.getTotal();
        totalPages=(totalPages/size)+1;
        return new BaseData9(brandBeans,totalPages);
    }

    @Override
    public Brand queryBrandDetail(Integer id) {
        return  brandMapper9.selectBrandDetail(id);
    }
}
