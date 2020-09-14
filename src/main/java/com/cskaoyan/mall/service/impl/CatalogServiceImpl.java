package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.bean_of_hanyuandong.Category;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.CategoryExample;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.wx.CurrentCatalogVo;
import com.cskaoyan.mall.bean.bean_of_hanyuandong.wx.IndexCatalogVo;
import com.cskaoyan.mall.mapper.CategoryMapper1;
import com.cskaoyan.mall.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    CategoryMapper1 categoryMapper;
    @Override
    public CurrentCatalogVo queryCategoryById(Integer id) {
        //查询所有一级目录
        Category category = categoryMapper.selectByPrimaryKey(id);
        //查询所有二级目录
        CategoryExample example2 = new CategoryExample();
        example2.createCriteria().andPidNotEqualTo(id);
        List<Category> categoryList = categoryMapper.selectByExample(example2);

        //合并一级目录和二级目录
        CurrentCatalogVo catalogVo = new CurrentCatalogVo(category, categoryList);
        return catalogVo;
    }

    @Override
    public IndexCatalogVo queryCategory() {
        //查询所有的目录
        CategoryExample example = new CategoryExample();
        List<Category> categories = categoryMapper.selectByExample(example);
        List<Category> parentList = new ArrayList<>();
        List<Category> subList = new ArrayList<>();
        for (Category category : categories) {
            if (category.getPid().intValue() == 0) {
                parentList.add(category);
            }
            subList.add(category);
        }
        Category currentCategory = parentList.get(0);
        Integer id = currentCategory.getId();
        Iterator<Category> iterator = subList.iterator();
        while (iterator.hasNext()) {
            Category next = iterator.next();
            if (next.getPid().intValue() != id) {
                iterator.remove();
            }
        }
        return new IndexCatalogVo(parentList,currentCategory,subList);
    }

    private CurrentCatalogVo mergeCatalog(List<Category> parentCategorys, List<Category> subCategorys) {
        CurrentCatalogVo catalogVo = new CurrentCatalogVo();
        for (Category parentCategory : parentCategorys) {
            List<Category> categories = new ArrayList<>();
            Iterator<Category> iterator = subCategorys.iterator();
            while (iterator.hasNext()){
                Category next = iterator.next();
                if (next.getPid().equals(parentCategory.getId())){
                    categories.add(next);
                    iterator.remove();
                }
            }

        }

        return null;
    }
}
