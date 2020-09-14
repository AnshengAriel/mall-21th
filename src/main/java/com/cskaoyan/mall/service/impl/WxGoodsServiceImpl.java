package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.bean_of_liuxufeng.Groupon;
import com.cskaoyan.mall.bean.bean_of_tangronghua.*;
import com.cskaoyan.mall.bean.bean_of_tangronghua.User;
import com.cskaoyan.mall.bean.bean_of_tangronghua.UserExample;
import com.cskaoyan.mall.bean.bean_of_wangminyi.*;
import com.cskaoyan.mall.bean.bean_of_wangyan.Issue;
import com.cskaoyan.mall.bean.bean_of_wangyan.IssueExample;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.service.WxGoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.cskaoyan.mall.utils.BeanUtils;
@Service
public class WxGoodsServiceImpl implements WxGoodsService {

    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    TopicMapper topicMapper;
    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    IssueMapper issueMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;
    @Autowired
    WxGoodsMapper wxGoodsMapper;
    @Autowired
    CollectMapper collectMapper;
    @Autowired
    UserMapper userMapper;

    public Integer getUserId() {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipals().getPrimaryPrincipal();
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        return users.get(0).getId();
    }

    @Override
    public Map<String,Object> goodsCategory(Integer id) {
        //得到一个对象
        Category category = categoryMapper.selectByPrimaryKey(id);
        Map<String,Object> map = new HashMap<>();

        //如果是子类对象
        if (category.getLevel().equals("L2")){
            Category category1 = categoryMapper.selectByPrimaryKey(category.getPid());
            CategoryExample categoryExample = new CategoryExample();
            categoryExample.createCriteria().andPidEqualTo(category.getPid());
            List<Category> wxParentCategories = categoryMapper.selectByExample(categoryExample);

            map.put("brotherCategory",wxParentCategories);
            map.put("currentCategory",category);
            map.put("parentCategory",category1);

            return map;
        }
        //得到子类集合
        CategoryExample categoryExample = new CategoryExample();
        //子类
        categoryExample.createCriteria().andPidEqualTo(id);
        List<Category> wxParentCategories = categoryMapper.selectByExample(categoryExample);

        //获取当前对象
        Category category1 = wxParentCategories.get(0);
        map.put("brotherCategory",wxParentCategories);
        map.put("currentCategory",category1);
        map.put("parentCategory",category);

        return map;

    }

    //相关商品，在商品详情下面有6个相关商品
    @Override
    public Map<String,Object> goodsRelated(Integer id) {

        Goods goods = goodsMapper.selectByPrimaryKey(id);
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andDeletedEqualTo(false).andCategoryIdEqualTo(goods.getCategoryId());
        //展示6条相关商品
        PageHelper.startPage(0,6);
        //根据条件查询出商品集合
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        List<WxGoods> wxGoods = null;
        try {
            wxGoods = BeanUtils.toBeanByFiledName(goodsList,WxGoods.class);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

//        List<WxGoodsList> wxGoodsList = null;
//        try {
//            wxGoodsList = BeanUtils.toBeanByFiledName(goodsList,WxGoodsList.class);
//        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
        Map<String,Object> map = new HashMap<>();
        map.put("goodsList",wxGoods);
        return map;
    }

    @Override
    public Map<String, Object> goodsList(Integer categoryId, Integer page, Integer size) {
        GoodsExample goodsExample = new GoodsExample();
        WxGoodsExample wxGoodsExample = new WxGoodsExample();
        goodsExample.createCriteria().andDeletedEqualTo(false).andCategoryIdEqualTo(categoryId).
                andIsOnSaleEqualTo(true);
        goodsExample.setOrderByClause("sort_order asc");
        PageHelper.startPage(page,size);
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        PageInfo<Goods> goodsPageInfo = new PageInfo<>(goodsList);

        List<WxGoodsList> wxGoodsList = null;
        try {
            wxGoodsList = BeanUtils.toBeanByFiledName(goodsList,WxGoodsList.class);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        List<Category> filterCategoryList = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("count",goodsPageInfo.getSize());
        map.put("goodsList",wxGoodsList);
        map.put("filterCategoryList",filterCategoryList);
        return map;
    }


    @Override
    public Map<String, Object> goodsDetail(Integer id) {
        //商品参数
        Map<String, Object> map = new HashMap<>();
        GoodsAttributeExample goodsAttributeExample = new GoodsAttributeExample();
        goodsAttributeExample.createCriteria().andGoodsIdEqualTo(id).andDeletedEqualTo(false);
        List<GoodsAttribute> goodsAttributes = goodsAttributeMapper.selectByExample(goodsAttributeExample);
        map.put("attribute",goodsAttributes);
        //品牌
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        Brand brand = new Brand();
        if (goods.getBrandId()==0){
            brand.setId(0);
        }else {
            brand = brandMapper.selectByPrimaryKey(goods.getBrandId());
        }
        map.put("brand",brand);
        //商品评论
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andValueIdEqualTo(id);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        CommentVo commentVo = new CommentVo();
        commentVo.setData(comments);commentVo.setCount(comments.size());
        map.put("comment",commentVo);
        //团购信息
        List<Groupon> groupons = new ArrayList<>();
        map.put("groupon",groupons);
        //商品信息
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.Goods2Info(goods);
        map.put("info",goodsInfo);
        //通用问题
        IssueExample issueExample = new IssueExample();
        issueExample.createCriteria().andDeletedEqualTo(false);
        List<Issue> issues = issueMapper.selectByExample(issueExample);
        map.put("issue",issues);
        //商品列表
        GoodsProductExample goodsProductExample = new GoodsProductExample();
        goodsProductExample.createCriteria().andGoodsIdEqualTo(id);
        List<GoodsProduct> goodsProducts = goodsProductMapper.selectByExample(goodsProductExample);
        List<GoodsProductVo> goodsProductVos = new ArrayList<>();
        for (GoodsProduct g:goodsProducts
        ) {
            GoodsProductVo goodsProductVo = new GoodsProductVo(g);
            goodsProductVos.add(goodsProductVo);
        }
        map.put("productList",goodsProductVos);
        //
        map.put("shareImage","");
        //规格列表
        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        goodsSpecificationExample.createCriteria().andGoodsIdEqualTo(id);
        List<GoodsSpecification> valueList = goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
        SpecificationList specificationList = new SpecificationList();
        specificationList.setValueList(valueList);
        specificationList.setName(valueList.get(0).getSpecification());
        ArrayList<SpecificationList> specificationLists = new ArrayList<>();
        specificationLists.add(specificationList);
        map.put("specificationList",specificationLists);
        //用户收藏
        CollectExample collectExample = new CollectExample();
        collectExample.createCriteria().andDeletedEqualTo(false).andUserIdEqualTo(getUserId()).andTypeEqualTo(new Integer(0).byteValue()).andValueIdEqualTo(id);
        List<Collect> collects = collectMapper.selectByExample(collectExample);
        if (id != null && (collects.size()!=0)){
            map.put("userHasCollect",1);
        }else {
            map.put("userHasCollect",0);
        }
        //map.put("userHasCollect",collects.get(id).getType());
        return map;
    }
}
