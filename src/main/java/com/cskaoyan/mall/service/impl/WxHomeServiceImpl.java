package com.cskaoyan.mall.service.impl;import com.cskaoyan.mall.bean.bean_of_liuxufeng.*;import com.cskaoyan.mall.bean.bean_of_tangronghua.CommentVo;import com.cskaoyan.mall.bean.bean_of_tangronghua.FloorGoodsList;import com.cskaoyan.mall.bean.bean_of_tangronghua.GoodsInfo;import com.cskaoyan.mall.bean.bean_of_tangronghua.SpecificationList;import com.cskaoyan.mall.bean.bean_of_wangminyi.*;import com.cskaoyan.mall.bean.bean_of_wangyan.Issue;import com.cskaoyan.mall.bean.bean_of_wangyan.IssueExample;import com.cskaoyan.mall.mapper.*;import com.cskaoyan.mall.service.WxHomeService;import com.cskaoyan.mall.service.WxHomeService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import java.util.ArrayList;import java.util.HashMap;import java.util.List;import java.util.Map;@Servicepublic class WxHomeServiceImpl implements WxHomeService {    @Autowired    AdMapper adMapper;    @Autowired    BrandMapper brandMapper;    @Autowired    GoodsMapper goodsMapper;    @Autowired    CouponMapper couponMapper;    @Autowired    CategoryMapper categoryMapper;    @Autowired    GrouponMapper grouponMapper;    @Autowired    TopicMapper topicMapper;    @Autowired    GoodsAttributeMapper goodsAttributeMapper;    @Autowired    CommentMapper commentMapper;    @Autowired    IssueMapper issueMapper;    @Autowired    GoodsProductMapper goodsProductMapper;    @Override    public Map<String, Object> homeIndex() {        Map<String,Object> map = new HashMap<>();        //获取banner数据        AdExample adExample = new AdExample();        adExample.createCriteria().andDeletedEqualTo(false).andEnabledEqualTo(true).andPositionEqualTo((byte)1);        List<Ad> banner = adMapper.selectByExample(adExample);        map.put("banner",banner);        //获取brandList的数据        BrandExample brandExample = new BrandExample();        brandExample.createCriteria().andDeletedEqualTo(false);        List<Brand> brands = brandMapper.selectByExample(brandExample);        map.put("brandList",brands);        //获取 new Goods List的数据        GoodsExample goodsExample = new GoodsExample();        goodsExample.createCriteria().andIsNewEqualTo(true).andDeletedEqualTo(false);        List<Goods> goods = goodsMapper.selectByExample(goodsExample);        map.put("newGoodsList",goods);        //获取 couponList 的数据        CouponExample couponExample = new CouponExample();        couponExample.createCriteria().andDeletedEqualTo(false);        List<Coupon> coupons = couponMapper.selectByExample(couponExample);        map.put("couponList",coupons);        CategoryExample categoryExample = new CategoryExample();        categoryExample.createCriteria().andDeletedEqualTo(false);        List<Category> categories = categoryMapper.selectByExample(categoryExample);        map.put("channel",categories);        GrouponExample grouponExample = new GrouponExample();        grouponExample.createCriteria().andDeletedEqualTo(false);        List<Groupon> groupons = grouponMapper.selectByExample(grouponExample);// ==>        map.put("",groupons);        GoodsExample goodsExample2 = new GoodsExample();        goodsExample2.createCriteria().andIsHotEqualTo(true).andDeletedEqualTo(false);        List<Goods> goods2 = goodsMapper.selectByExample(goodsExample2);        map.put("hotGoodsList",goods2);        TopicExample topicExample = new TopicExample();        topicExample.createCriteria().andDeletedEqualTo(false);        List<Topic> topics = topicMapper.selectByExample(topicExample);        map.put("topicList",topics);        /**==>      * 暂时不实现，根据category_id 和 brand_id 去分类         */        FloorGoodsList floorGoodsList = new FloorGoodsList();        map.put("floorGoodsList",floorGoodsList);        return map;    }    @Override    public Map<String, Object> goodsCount() {        GoodsExample goodsExample = new GoodsExample();        goodsExample.createCriteria().andDeletedEqualTo(false);        long count = goodsMapper.countByExample(goodsExample);        Map<String,Object> map = new HashMap<>();        map.put("goodsCount",count);        return map;    }}