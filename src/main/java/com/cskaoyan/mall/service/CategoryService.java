package com.cskaoyan.mall.service;
import com.cskaoyan.mall.bean.bean_of_wangyan.*;
import java.util.Map;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.*;

import java.util.List;

public interface CategoryService {
    List<CategoryListVo> queryCategoryList();

    List<CategoryL1Vo> queryCategoryL1Vo();

    CategoryCreateVo createCategory(CategoryCreateBo categoryCreateBo);

    void logicDeleteCategoryById(Integer id);

    /**
     * 从二级目录更新到二级目录
     * @param updateBo
     */
    void updateCategory(CategoryUpdateTwoBo updateBo);

    /**
     * 从一级目录更新到一级目录
     * @param updateBo
     */
    void updateCategory2(CategoryListVo updateBo);

    /**
     * 从一级目录更新到二级目录，或从二级目录更新到一级目录
     * @param updateBo
     */
    void updateCategory3(CategoryUpdateBo updateBo);


    BaseData queryIssue(Integer page, Integer limit, String sort, String order);

    Issue createIssue(Issue issue);

    Issue updateIssue(Issue issue);

    void deleteIssue(Issue issue);

    BaseData queryKeyword(Integer page, Integer limit, String sort, String order);

    Keyword createKeyword(Keyword keyword);

    Keyword updateKeyword(Keyword keyword);

    void deleteKeyword(Keyword keyword);

    BaseData queryOrder(Integer page, Integer limit, String sort, String order, Integer userId, String orderSn);

    OrderDetailVo orderDetail(Integer id);

    Order orderRefund(Map map);

    Order orderShip(Map map);

}
