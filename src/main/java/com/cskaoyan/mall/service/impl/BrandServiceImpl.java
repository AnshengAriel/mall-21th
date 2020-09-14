package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.bean_of_wangyan.*;
import com.cskaoyan.mall.mapper.BrandMapper1;
import com.cskaoyan.mall.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandMapper1 brandMapper1;

    @Override
    public BaseData queryBrandList(Integer page, Integer limit, String sort, String order, Integer id, String name) {
        BrandExample brandExample = new BrandExample();
        brandExample.setOrderByClause(sort + " " + order);
        BrandExample.Criteria criteria = brandExample.createCriteria();
        if (id != null) {
            criteria.andIdEqualTo(id);
        }
        if (name != null) {
            criteria.andNameLike("%" + name + "%");
        }
        System.out.println(criteria);
        PageHelper.startPage(page, limit);//执行查询前使用分页
        List<Brand> brands = brandMapper1.selectByExample(brandExample);
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
        long total = pageInfo.getTotal();
        return new BaseData(brands, total);
    }
    @Override
    public Brand createBrand(Brand brand) {
        brand.setAddTime(new Date());
        brand.setDeleted(false);
        brand.setUpdateTime(new Date());
        brandMapper1.insert(brand);
        return brand;
    }

    @Override
    public Brand updateBrand(Brand brand) {
        brand.setUpdateTime(new Date());
        brandMapper1.updateByPrimaryKey(brand);
        return brand;
    }

    @Override
    public void deleteBrand(Brand brand) {
        brandMapper1.deleteByPrimaryKey(brand.getId());
    }
}