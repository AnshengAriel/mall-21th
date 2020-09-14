package com.cskaoyan.mall.controller.wx_brand_jiayi;


import com.cskaoyan.mall.bean.BaseResoVo;
import com.cskaoyan.mall.bean.wx_brand_jiayi.BaseData9;
import com.cskaoyan.mall.bean.wx_brand_jiayi.Brand;
import com.cskaoyan.mall.bean.wx_brand_jiayi.Brandd;
import com.cskaoyan.mall.service.wx_brand_jiayi.BrandService9;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx")
public class BrandController {


    @Autowired
    BrandService9 brandService9;


    @RequestMapping("brand/list")
    public BaseResoVo brandList(Integer page,Integer size){

        BaseData9 baseData9 = brandService9.querytBrand(page, size);
        return BaseResoVo.ok(baseData9);
    }


    @RequestMapping("brand/detail")
    public BaseResoVo brandDetail(Integer id){

        Brand brand = brandService9.queryBrandDetail(id);
        return  BaseResoVo.ok(new Brandd(brand));
    }
}
