package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.bean_of_wangminyi.*;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;

    @Autowired
    GoodsProductMapper goodsProductMapper;

    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;

    @Autowired
    CategoryMapper categoryMapper;

    //page第几页、limit每页数据数-用于分页，sort、order-用于排序
    @Override
    public BaseData queryGoods(Integer page, Integer limit, String sort, String order) {
        //排序的字符串的拼接
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.setOrderByClause(sort + " " + order);

        //执行查询之前先分页
        PageHelper.startPage(page, limit);

        //根据分页条件进行查询
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);

        //获取总数据量
        PageInfo<Goods> pageInfo = new PageInfo<>(goods);
        long total = pageInfo.getTotal();

        return new BaseData(goods, total);
    }

    @Override
    public List<CategoryVoList> queryCat() {
        return null;
    }

    @Override
    public List<CategoryChildrenVoList> queryBrands() {
        return null;
    }

    @Override
    public  Goods  queryGoodsById(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer queryCategoryIdByGoodsId(Integer id) {
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        return goods.getCategoryId();
    }

    @Override
    public Integer queryPidByCategoryId(Integer categoryId) {
        return null;
    }


    @Override
    public int deleteGoods(Goods goods) {
        goods.setDeleted(true);
        return goodsMapper.updateByPrimaryKey(goods);
    }

    @Override
    public int insertGoods(Goods goods) {
        return goodsMapper.insertSelective(goods);
    }

    @Override
    public int getInsertId() {
        return 0;
    }

    @Override
    public int insertGoodsSpecifications(List<GoodsSpecification> specifications, int goodsId) {
        int row = 0;
        for (GoodsSpecification specification : specifications) {
            specification.setGoodsId(goodsId);
            row += goodsSpecificationMapper.insertSelective(specification);
        }
        return row;
    }

    @Override
    public int insertGoodsProducts(List<GoodsProduct> products, int goodsId) {
        int row = 0;
        for (GoodsProduct product : products) {
            product.setGoodsId(goodsId);
            row += goodsProductMapper.insertSelective(product);
        }
        return row;
    }

    @Override
    public int insertAttributes(List<GoodsAttribute> attributes, int goodsId) {
        int row = 0;
        for (GoodsAttribute attribute : attributes) {
            attribute.setGoodsId(goodsId);
            row += goodsAttributeMapper.insertSelective(attribute);
        }
        return row;
    }
}
