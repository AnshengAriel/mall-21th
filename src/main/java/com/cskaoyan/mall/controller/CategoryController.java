package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.BaseRespVo;

import com.cskaoyan.mall.bean.bean_of_hanyuandong.*;
import com.cskaoyan.mall.service.CategoryService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.cskaoyan.mall.bean.bean_of_wangyan.*;
import com.cskaoyan.mall.service.CategoryService;
import com.cskaoyan.mall.service.impl.BrandServiceImpl;
import com.cskaoyan.mall.service.impl.RegionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 商场管理
 */
@RestController
@RequestMapping("admin")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("category/list")
    public BaseRespVo list() {
        List<CategoryListVo> categoryListVos = categoryService.queryCategoryList();
        return BaseRespVo.ok(categoryListVos);
    }

    @RequestMapping("category/l1")
    public BaseRespVo l1() {
        List<CategoryL1Vo> categoryL1VoList = categoryService.queryCategoryL1Vo();
        return BaseRespVo.ok(categoryL1VoList);
    }

    @PostMapping("category/create")
    public BaseRespVo create(@RequestBody CategoryCreateBo categoryCreateBo) {
        CategoryCreateVo createVo = categoryService.createCategory(categoryCreateBo);
        return BaseRespVo.ok(createVo);
    }

    @PostMapping("category/delete")
    public BaseRespVo deleteTwo(@RequestBody CategoryVo categoryVo) {
        categoryService.logicDeleteCategoryById(categoryVo.getId());
        return BaseRespVo.ok();
    }

    @PostMapping(value = "category/delete", params = "children")
    public BaseRespVo deleteOne(@RequestBody CategoryListVo listVo) {
        categoryService.logicDeleteCategoryById(listVo.getId());
        return BaseRespVo.ok();
    }
    @Autowired
    RegionServiceImpl regionService;

    @Autowired
    BrandServiceImpl brandService;
    /**
     * 通用问题
     */
    @RequestMapping("issue/list")
    public BaseRespVo listIssue(Integer page, Integer limit, String sort, String order){
        //当前页码，一页显示多少条数据，排序order by add_time desc
        BaseData baseData = categoryService.queryIssue(page, limit, sort, order);
        return BaseRespVo.ok(baseData);
    }

    @PostMapping("issue/create")
    public BaseRespVo createIssue(@RequestBody  Issue issue){
          //以Issue对象接收前端传过来的json对象，springmvc会智能的将符合要求的数据装配进该Issue对象中
          Issue issueData = categoryService.createIssue(issue);
          return BaseRespVo.ok(issueData);
    }

    @RequestMapping("issue/update")
    public BaseRespVo updateIssue(@RequestBody Issue issue){
        Issue issueData = categoryService.updateIssue(issue);
        return BaseRespVo.ok(issueData);
    }

    @RequestMapping("issue/delete")
    public BaseRespVo deleteIssue(@RequestBody Issue issue){
        categoryService.deleteIssue(issue);
        return BaseRespVo.ok();
    }

    /**
     * 从二级目录更新到二级目录
     * @param updateBo
     * @return
     */
    @PostMapping("category/update")
    public BaseRespVo updateCategory1(@RequestBody CategoryUpdateTwoBo updateBo) {
        categoryService.updateCategory(updateBo);
        return BaseRespVo.ok();
    }
    @RequestMapping("keyword/list")
    public BaseRespVo listKeyword(Integer page, Integer limit, String sort, String order){
        //当前页码，一页显示多少条数据，排序order by add_time desc
        BaseData baseData = categoryService.queryKeyword(page, limit, sort, order);
        return BaseRespVo.ok(baseData);
    }

    @PostMapping("keyword/create")
    public BaseRespVo createKeyword(@RequestBody Keyword keyword){
        //以Issue对象接收前端传过来的json对象，springmvc会智能的将符合要求的数据装配进该Issue对象中
        Keyword keywordData = categoryService.createKeyword(keyword);
        return BaseRespVo.ok(keywordData);
    }

    @RequestMapping("keyword/update")
    public BaseRespVo updateKeyword(@RequestBody Keyword keyword){
        Keyword keywordData = categoryService.updateKeyword(keyword);
        return BaseRespVo.ok(keywordData);
    }

    @RequestMapping("keyword/delete")
    public BaseRespVo deleteKeyword(@RequestBody Keyword keyword){
        categoryService.deleteKeyword(keyword);
        return BaseRespVo.ok();
    }

    /**
     * 从一级目录更新到一级目录
     * @param updateBo
     * @return
     */
    @PostMapping(value = "category/update" ,params = "children")
    public BaseRespVo updateCategory2(@RequestBody CategoryListVo updateBo){
        categoryService.updateCategory2(updateBo);
        return BaseRespVo.ok();
    }

    /**
     * 从一级目录更新到二级目录，或从二级目录更新到一级目录
     * @param updateBo
     * @return
     */
    @PostMapping(value = "category/update" ,params = {"pid","children"})
    public BaseRespVo updateCategory3(@RequestBody CategoryUpdateBo updateBo){
        categoryService.updateCategory3(updateBo);
        return BaseRespVo.ok();
    }

    @RequestMapping("order/list")
    public BaseRespVo listOrder(Integer page, Integer limit, String sort, String order,Integer userId,String orderSn){
        //当前页码，一页显示多少条数据，排序order by add_time desc
        BaseData baseData = categoryService.queryOrder(page, limit, sort, order,userId,orderSn);
        return BaseRespVo.ok(baseData);
    }

    @RequestMapping("order/detail")
    public BaseRespVo orderDetail(Integer id){
        OrderDetailVo orderDetailVo = categoryService.orderDetail(id);
        return BaseRespVo.ok(orderDetailVo);
    }

    @RequestMapping("order/refund")
    public BaseRespVo orderRefund(@RequestBody Map map){
        Order vo = categoryService.orderRefund(map);
        return BaseRespVo.ok(vo);
    }

    @RequestMapping("order/ship")
    public BaseRespVo orderShip(@RequestBody Map map){
        Order vo = categoryService.orderShip(map);
        return BaseRespVo.ok(vo);
    }

    /**行政区域admin/Region9
     List,clist
     * */
    @GetMapping("region/list")
    public BaseRespVo queryList(){
        RegionVo regionVo = regionService.queryList(); //这里就从数据库取得数据，封装好data
        return BaseRespVo.ok(regionVo.getData());
    }

    /**
     品牌制造商admin/brand
     list,create,update,delete*/
    @GetMapping("brand/list")
    public BaseRespVo queryBrandList(Integer page,Integer limit,String sort ,String order,Integer id,String name){
        BaseData baseData = brandService.queryBrandList(page,limit,sort,order,id,name); //这里就从数据库取得数据，封装好data
        return BaseRespVo.ok(baseData);
    }

    @PostMapping("brand/create")
    public BaseRespVo createBrand(@RequestBody Brand brand){
        Brand brandeData = brandService.createBrand(brand);
        return BaseRespVo.ok(brandeData);
    }

    @RequestMapping("brand/update")
    public BaseRespVo updateBrand(@RequestBody Brand brand){
        Brand brandeData = brandService.updateBrand(brand);
        return BaseRespVo.ok(brandeData);
    }

    @RequestMapping("brand/delete")
    public BaseRespVo deleteBrand(@RequestBody Brand brand){
        brandService.deleteBrand(brand);
        return BaseRespVo.ok();
    }
}
