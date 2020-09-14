package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.bean_of_wangminyi.BaseRespVo;
import com.cskaoyan.mall.bean.bean_of_wangminyi.*;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 商品管理
 */
@RestController
@RequestMapping("admin/goods")
public class GoodsController {
    /*
    商品列表admin/goods/list
    商品上架admin/goods/create
    商品更新admin/goods/update
    商品删除admin/goods/delete
    商品详情admin/goods/detail
    商品品牌admin/goods/catAndBrand(为上架页面提供品牌信息)
    商品评论admin/comment
            list,delete
    图片上传 admin/storage/create
    */


    BaseRespVo baseRespVo = new BaseRespVo();
    @Autowired
    GoodsService goodsService;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;
    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;

    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    BrandMapper brandMapper;


    //    商品列表
    //    Request URL: http://182.92.235.201:8083/admin/goods/list?
    //    page=1&limit=20&sort=add_time&order=desc

    @RequestMapping("list")
    public BaseRespVo list(Integer page, Integer limit, String sort, String order) {
        BaseData baseData = goodsService.queryGoods(page, limit, sort, order);
        return BaseRespVo.ok(baseData);
    }

    //    商品上架
    /*{goods: {picUrl: "http://182.92.235.201:8083/wx/storage/fetch/shz52nyy27kh509pjuha.jpg",…},…}
attributes: [{attribute: "1", value: "1"}]
goods: {picUrl: "http://182.92.235.201:8083/wx/storage/fetch/shz52nyy27kh509pjuha.jpg",…}
products: [{id: 0, specifications: ["标准"], price: "1", number: "1",…}]
specifications: [{specification: "规格", value: "标准", picUrl: ""}]*/
    @PostMapping("create")
    public BaseRespVo create(@RequestBody GoodsCreateBo goodsCreateBo) {
        List<Goods> list = new ArrayList<>();
        Goods queryGoods = goodsService.queryGoodsById(Integer.valueOf(goodsCreateBo.getGoods().getGoodsSn()));
        list.add(queryGoods);
        if (list.get(0) == null) {
            //gallery参数需要文件上传接口来获得的url
            Goods goods = new Goods(Integer.valueOf(goodsCreateBo.getGoods().getGoodsSn()), goodsCreateBo.getGoods().getGoodsSn(), goodsCreateBo.getGoods().getName(),
                    goodsCreateBo.getGoods().getCategoryId(), goodsCreateBo.getGoods().getBrandId(),
                    "http://182.92.235.201:8083/wx/storage/fetch/s7q45cfemn1b2jb5ml2i.jpg", goodsCreateBo.getGoods().getKeywords(),
                    goodsCreateBo.getGoods().getBrief(), goodsCreateBo.getGoods().getIsOnSale(), goodsCreateBo.getGoods().getSortOrder(),
                    goodsCreateBo.getGoods().getPicUrl(), goodsCreateBo.getGoods().getShareUrl(), goodsCreateBo.getGoods().getIsNew(),
                    goodsCreateBo.getGoods().getIsHot(), goodsCreateBo.getGoods().getUnit(), goodsCreateBo.getGoods().getCounterPrice(),
                    goodsCreateBo.getGoods().getRetailPrice(), new Date(), new Date(),
                    goodsCreateBo.getGoods().getDeleted(), goodsCreateBo.getGoods().getDetail()
            );
            GoodsExample goodsExample = new GoodsExample();
            goodsExample.createCriteria().andIdEqualTo(goods.getId());
            return baseRespVo.ok();
        }

        baseRespVo.setErrno(601);
        baseRespVo.setErrmsg("商品已存在");
        return baseRespVo;
    }

    @RequestMapping("update")
    public BaseRespVo update(@RequestBody GoodsCreateBo goodsCreateBo) {
        Goods goods = new Goods(Integer.valueOf(goodsCreateBo.getGoods().getGoodsSn()), goodsCreateBo.getGoods().getGoodsSn(), goodsCreateBo.getGoods().getName(),
                goodsCreateBo.getGoods().getCategoryId(), goodsCreateBo.getGoods().getBrandId(),
                "http://182.92.235.201:8083/wx/storage/fetch/s7q45cfemn1b2jb5ml2i.jpg", goodsCreateBo.getGoods().getKeywords(),
                goodsCreateBo.getGoods().getBrief(), goodsCreateBo.getGoods().getIsOnSale(), goodsCreateBo.getGoods().getSortOrder(),
                goodsCreateBo.getGoods().getPicUrl(), goodsCreateBo.getGoods().getShareUrl(), goodsCreateBo.getGoods().getIsNew(),
                goodsCreateBo.getGoods().getIsHot(), goodsCreateBo.getGoods().getUnit(), goodsCreateBo.getGoods().getCounterPrice(),
                goodsCreateBo.getGoods().getRetailPrice(), new Date(), new Date(),
                goodsCreateBo.getGoods().getDeleted(), goodsCreateBo.getGoods().getDetail()
        );
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIdEqualTo(goods.getId());
        return baseRespVo.ok();
    }

    //    商品品牌
    /*data: {,…}
brandList: [{value: 1001000, label: "MUJI制造商"}, {value: 1001002, label: "内野制造商"},…]
categoryList: [{value: 1005000, label: "居家",…}, {value: 1005001, label: "餐厨",…}, {value: 1005002, label: "饮食",…},…]
errmsg: "成功"
errno: 0*/
    @RequestMapping("catAndBrand")
    public BaseRespVo catAndBrand() {
        HashMap<String, Object> map = new HashMap<>();

        List<BrandListVo> brandList = new ArrayList<>();
        BrandExample brandExample = new BrandExample();
        brandExample.createCriteria().andIdIsNotNull();
        List<Brand> tempBrandList = brandMapper.selectByExample(brandExample);
        for (Brand brand : tempBrandList) {
            BrandListVo brandListVo = new BrandListVo();
            brandListVo.setLabel(brand.getName());
            brandListVo.setValue(brand.getId());
            brandList.add(brandListVo);
        }

        //pid等于0，说明是大类
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andPidEqualTo(0);
        List<Category> tempCategoryList = categoryMapper.selectByExample(categoryExample);
        List<CategoryVoList> categoryList = new ArrayList<>();
        for (Category category : tempCategoryList) {

            //从查询到的大类里取出需要的数据封装到categoryVoList
            CategoryVoList categoryVoList = new CategoryVoList();
            categoryVoList.setLabel(category.getName());
            categoryVoList.setValue(category.getId());

            //小类的pid等于大类的id
            CategoryExample tempExample = new CategoryExample();
            tempExample.createCriteria().andPidEqualTo(category.getId());
            List<Category> tempList = categoryMapper.selectByExample(tempExample);
            List<CategoryChildrenVoList> childrenList = new ArrayList<>();
            for (Category tempCategory : tempList) {
                CategoryChildrenVoList categoryChildrenVoList = new CategoryChildrenVoList();
                categoryChildrenVoList.setValue(tempCategory.getId());
                categoryChildrenVoList.setLabel(tempCategory.getName());
                childrenList.add(categoryChildrenVoList);
            }
            categoryVoList.setChildren(childrenList);
            categoryList.add(categoryVoList);
        }
        map.put("brandList", brandList);
        map.put("categoryList", categoryList);
        return baseRespVo.ok(map);
    }

    //    商品删除
    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody Goods goods) {
        BaseRespVo baseRespVo = new BaseRespVo();
        int row = goodsService.deleteGoods(goods);
        if (row == 1) {
            baseRespVo.setErrno(0);
            baseRespVo.setErrmsg("成功");
        } else {
            baseRespVo.setErrno(500);
            baseRespVo.setErrmsg("失败");
        }
        return baseRespVo;
    }

    @RequestMapping("detail")
    public BaseRespVo detail(@RequestParam("id") Integer id) {

        GoodsAttributeExample goodsAttributeExample = new GoodsAttributeExample();
        goodsAttributeExample.createCriteria().andGoodsIdEqualTo(id);
        List<GoodsAttribute> attributes = goodsAttributeMapper.selectByExample(goodsAttributeExample);

        List<Integer> categoryIds = new ArrayList<>();
        Integer categoryId = goodsService.queryCategoryIdByGoodsId(id);
        Integer pid = goodsService.queryPidByCategoryId(categoryId);
        categoryIds.add(categoryId);
        categoryIds.add(pid);

        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIdEqualTo(id);
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);

        GoodsProductExample goodsProductExample = new GoodsProductExample();
        goodsProductExample.createCriteria().andGoodsIdEqualTo(id);
        List<GoodsProduct> products = goodsProductMapper.selectByExample(goodsProductExample);

        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        goodsSpecificationExample.createCriteria().andGoodsIdEqualTo(id);
        List<GoodsSpecification> specifications = goodsSpecificationMapper.selectByExample(goodsSpecificationExample);

        HashMap<String, Object> map = new HashMap<>();
        map.put("attributes", attributes);
        map.put("categoryIds", categoryIds);
        map.put("goods", goods);
        map.put("products", products);
        map.put("specifications", specifications);

        return baseRespVo.ok(map);
    }

}
