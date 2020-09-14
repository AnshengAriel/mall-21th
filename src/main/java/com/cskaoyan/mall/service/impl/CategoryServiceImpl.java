package com.cskaoyan.mall.service.impl;


import com.cskaoyan.mall.bean.bean_of_hanyuandong.*;
import com.cskaoyan.mall.mapper.CategoryMapper1;
import com.cskaoyan.mall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cskaoyan.mall.bean.bean_of_tangronghua.User;
import com.cskaoyan.mall.bean.bean_of_wangyan.*;
import com.cskaoyan.mall.mapper.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.Map;
import com.cskaoyan.mall.order_constants.*;
import static org.springframework.util.StringUtils.isEmpty;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper1 categoryMapper1;

    @Override
    public List<CategoryListVo> queryCategoryList() {
        //查询一级目录
        List<CategoryListVo> categoryVoList = categoryMapper1.selectList();
        int size = categoryVoList.size();
        //查询二级目录
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andPidNotEqualTo(0).andDeletedEqualTo(false);
        List<Category> categories = categoryMapper1.selectByExample(categoryExample);

        for (int i = 0; i < size; i++) {
            //子目录的list
            List<CategoryVo> child = new ArrayList<>();
            for (Category category : categories) {
                CategoryVo categoryVo = new CategoryVo(category.getId(), category.getName(), category.getKeywords(), category.getDesc(), category.getIconUrl(), category.getPicUrl(), category.getLevel());
                CategoryListVo categoryListVo = categoryVoList.get(i);
                //是此一级目录的子目录就添加到子目录的list中
                if (categoryListVo.getId().equals(category.getPid())) {
                    child.add(categoryVo);
                }
            }
            //将子目录的list设置到相应的父目录
            categoryVoList.get(i).setChildren(child);
        }
        return categoryVoList;
    }

    @Override
    public List<CategoryL1Vo> queryCategoryL1Vo() {
        List<CategoryL1Vo> l1VoList = categoryMapper1.selectCategoryL1Vo();
        return l1VoList;
    }

    @Override
    public CategoryCreateVo createCategory(CategoryCreateBo createBo) {
        Date date = new Date(System.currentTimeMillis());
        Category category = new Category(null, createBo.getName(), createBo.getKeywords(), createBo.getDesc(), createBo.getPid(), createBo.getIconUrl(), createBo.getPicUrl(), createBo.getLevel(), null, date, date, false);
        int id = categoryMapper1.insert(category);
        CategoryCreateVo createVo = new CategoryCreateVo(id, createBo.getName(), createBo.getKeywords(), createBo.getDesc(), createBo.getPid(), createBo.getIconUrl(), createBo.getPicUrl(), createBo.getLevel(), date, date, false);
        return createVo;
    }

    @Override
    public void logicDeleteCategoryById(Integer id) {
        categoryMapper1.logicDeleteCategoryById(id);
    }

    @Override
    public void updateCategory(CategoryUpdateTwoBo updateBo) {
        Date date = new Date(System.currentTimeMillis());
        Category category = new Category(updateBo.getId(), updateBo.getName(), updateBo.getKeywords(), updateBo.getDesc(), updateBo.getPid(), updateBo.getIconUrl(), updateBo.getPicUrl(), updateBo.getLevel(), null, null, date, false);
        categoryMapper1.updateByPrimaryKey(category);
    }

    @Override
    public void updateCategory2(CategoryListVo updateBo) {
        Date date = new Date(System.currentTimeMillis());
        Category category = new Category(updateBo.getId(), updateBo.getName(), updateBo.getKeywords(), updateBo.getDesc(), null, updateBo.getIconUrl(), updateBo.getPicUrl(), updateBo.getLevel(), null, null, date, false);
        categoryMapper1.updateByPrimaryKey(category);
    }

    @Override
    public void updateCategory3(CategoryUpdateBo updateBo) {
        Date date = new Date(System.currentTimeMillis());
        Category category = new Category(updateBo.getId(), updateBo.getName(), updateBo.getKeywords(), updateBo.getDesc(), updateBo.getPid(), updateBo.getIconUrl(), updateBo.getPicUrl(), updateBo.getLevel(), null, null, date, false);
        categoryMapper1.updateByPrimaryKey(category);
    }
    IssueMapper issueMapper;

    @Autowired
    KeywordMapper keywordMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderGoodsMapper orderGoodsMapper;

    @Override
    public BaseData queryIssue(Integer page, Integer limit, String sort, String order) {
        IssueExample issueExample = new IssueExample();
        issueExample.setOrderByClause(sort + " "+ order);

        PageHelper.startPage(page,limit);
        List<Issue> issues = issueMapper.selectByExample(issueExample);
        PageInfo<Issue> pageInfo = new PageInfo<>(issues);
        long total = pageInfo.getTotal();
        return new BaseData(issues,total);
    }

    @Override
    public Issue createIssue(Issue issue) {
        //new Date()获取当前日期
        issue.setAddTime(new Date());
        issue.setDeleted(false);
        issue.setUpdateTime(new Date());
        issueMapper.insert(issue);
        return issue;
    }

    @Override
    public Issue updateIssue(Issue issue) {
        issue.setUpdateTime(new Date());
        issueMapper.updateByPrimaryKey(issue);
        return issue;
    }

    @Override
    public void deleteIssue(Issue issue) {
        issueMapper.deleteByPrimaryKey(issue.getId());
    }

    @Override
    public BaseData queryKeyword(Integer page, Integer limit, String sort, String order) {
        KeywordExample keywordExample = new KeywordExample();
        keywordExample.setOrderByClause(sort + " " + order);

        PageHelper.startPage(page,limit);
        List<Keyword> keywords = keywordMapper.selectByExample(keywordExample);
        PageInfo<Keyword> pageInfo = new PageInfo<>(keywords);
        long total = pageInfo.getTotal();
        return new BaseData(keywords,total);
    }

    @Override
    public Keyword createKeyword(Keyword keyword) {
        keyword.setAddTime(new Date());
        //Deleted是一个假删，把delete改为1,前端就不显示了，但是数据库里的数据还在
        keyword.setDeleted(false);
        keyword.setUpdateTime(new Date());
        keyword.setSortOrder(101);
        keywordMapper.insert(keyword);
        return keyword;
    }

    @Override
    public Keyword updateKeyword(Keyword keyword) {
        keyword.setUpdateTime(new Date());
        keywordMapper.updateByPrimaryKey(keyword);
        return keyword;
    }

    @Override
    public void deleteKeyword(Keyword keyword) {
        keywordMapper.deleteByPrimaryKey(keyword.getId());
    }

    @Override
    public BaseData queryOrder(Integer page, Integer limit, String sort, String order, Integer userId, String orderSn) {
        OrderExample orderExample = new OrderExample();
        orderExample.setOrderByClause(sort + " " + order);

        OrderExample.Criteria criteria = orderExample.createCriteria();
        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (orderSn != null) {
            criteria.andOrderSnEqualTo(orderSn);
        }
        PageHelper.startPage(page,limit);
        List<Order> orders = orderMapper.selectByExample(orderExample);
        PageInfo<Order> pageInfo = new PageInfo<>(orders);
        long total = pageInfo.getTotal();
        return new BaseData(orders,total);
    }

    @Override
    public OrderDetailVo orderDetail(Integer id) {

        Order order = orderMapper.selectByPrimaryKey(id);

        User user = userMapper.selectByPrimaryKey(order.getUserId());

        OrderGoodsExample orderGoodsExample = new OrderGoodsExample();
        orderGoodsExample.createCriteria().andOrderIdEqualTo(id).andDeletedEqualTo(false);
        List<OrderGoods> orderGoods = orderGoodsMapper.selectByExample(orderGoodsExample);

        OrderDetailVo orderDetailVo = new OrderDetailVo();
        orderDetailVo.setOrder(order);
        orderDetailVo.setUser(user);
        orderDetailVo.setOrderGoods(orderGoods);
        return orderDetailVo;
    }

    @Override
    public Order orderRefund(Map map) {
        Order order = new Order();
        order.setId((Integer) map.get("orderId"));
        order.setOrderStatus(OrderStatusConstants.ORDER_REFUNDED);
        order.setUpdateTime(new Date());
        //将数据更新
        orderMapper.updateByPrimaryKeySelective(order);
        //将更新后的数据返回
        Order order1 =  orderMapper.selectByPrimaryKey(order.getId());
        return order1;
    }

    @Override
    public Order orderShip(Map map) {
        Order order = new Order();
        order.setId((Integer) map.get("orderId"));
        order.setShipChannel((String) map.get("shipChannel"));
        order.setOrderStatus(OrderStatusConstants.ORDER_SHIPPED);
        order.setUpdateTime(new Date());
        orderMapper.updateByPrimaryKeySelective(order);
        orderMapper.selectByPrimaryKey(order.getId());
        return order;
    }
}
