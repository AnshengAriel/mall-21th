package com.cskaoyan.mall.controller;
import com.cskaoyan.mall.bean.BaseRespVo;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.*;
import com.cskaoyan.mall.service.impl.*;
import com.cskaoyan.mall.utils.UploadUtil2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 推广管理
 */
@RestController
@RequestMapping("/admin")
public class IssueController {
    /**
     * 广告管理admin/ad
     * 	list,create,read(详情),update,delete
     * 优惠券管理admin/coupon
     * list,listuser(查询用户),create,read(详情),update,delete
     * 专题管理admin/topic
     * list,create,read(详情),update,delete
     * 团购admin/groupon
     * list,listRecord(详情),create,update,delete
     */
    @Autowired
    AdServiceImpl adService;
    /****************************************      Ad ----- 广告管理    ********************************************/
    @GetMapping("ad/list")
    public BaseRespVo IssueAd(AdBo AdBo){
        AdVo adVo = adService.queryAd(AdBo);
        return BaseRespVo.ok(adVo);
    }


    @PostMapping("ad/update")
    public BaseRespVo updateAd(@RequestBody Ad ad){ //增加RequestBody ，响应的是json数据，不再是Model And View
        int i = adService.updateAdById(ad);
        if(i==0)return null;
        return BaseRespVo.ok();
    }

    @PostMapping("ad/delete")
    public BaseRespVo deleteAd(@RequestBody Ad ad){
        int i = adService.deleteAdById(ad);
        if(i==0)return null;
        return BaseRespVo.ok();
    }

    @PostMapping("ad/create")
    public BaseRespVo createAd(@RequestBody Ad ad){
        Ad ad1 = adService.createAd(ad);
        return BaseRespVo.ok(ad1);
    }


    /****************************************    coupon ----- 优惠券管理    ********************************************/

    @Autowired
    CouponServiceImpl couponService;

    @GetMapping("coupon/list")
    public BaseRespVo IssueCoupon(CouponBo coupon){
        CouponVo couponVo = couponService.queryCoupon(coupon);
        return BaseRespVo.ok(couponVo);
    }

    @PostMapping("coupon/update")
    public BaseRespVo updateCoupon(@RequestBody Coupon coupon){ //增加RequestBody ，响应的是json数据，不再是Model And View
        int i = couponService.updateCouponById(coupon);
        if(i==0)return null;
        CouponList2StringBo couponList2StringBo = new CouponList2StringBo(coupon);
        return BaseRespVo.ok(couponList2StringBo);
    }

    @GetMapping("coupon/read")
    public BaseRespVo readCoupon(@RequestParam("id")Integer id){
        CouponList2StringBo couponList2StringBo = couponService.selectCouponById(id);
        return BaseRespVo.ok(couponList2StringBo);
    }

    /**
     * 此处要对接用户表---------------------------
     * @return
     */
    @GetMapping("coupon/listuser")
    public BaseRespVo listuserCoupon(CouponBo couponBo){
        /*要对接用户表，这里写个空接口*/
        return BaseRespVo.ok(new BaseList());
    }

    @PostMapping("coupon/delete")
    public BaseRespVo deleteCoupon(@RequestBody Coupon coupon){
        int i = couponService.deleteCouponById(coupon);
        if(i==0)return null;
        return BaseRespVo.ok();
    }

    @PostMapping("coupon/create")
    public BaseRespVo createCoupon(@RequestBody CouponList2StringBo coupon){
        int id = couponService.createCoupon(coupon);
        coupon.setId(id);
        return BaseRespVo.ok(coupon);
    }

    /****************************************    topic ----- 专题管理    ********************************************/

    @Autowired
    TopicServiceImpl topicService;

    @RequestMapping ("topic/list")
    public BaseRespVo IssueTopic(TopicBo topicBo){
        TopicVo topicVo = topicService.queryTopic(topicBo);
        return BaseRespVo.ok(topicVo);
    }
    @PostMapping("topic/update")
    public BaseRespVo updateTopic(@RequestBody Topic topic){ //增加RequestBody ，响应的是json数据，不再是Model And View
        int i = topicService.updateTopicById(topic);
        if(i==0)return null;
        return BaseRespVo.ok();
    }

    @PostMapping("topic/delete")
    public BaseRespVo deleteTopic(@RequestBody Topic topic){
        int i = topicService.deleteTopicById(topic);
        if(i==0)return null;
        return BaseRespVo.ok();
    }

    @PostMapping("topic/create")
    public BaseRespVo createTopic(@RequestBody TopicWithGoods topic){
        TopicWithGoods topic1 = topicService.insertTopic(topic);
        return BaseRespVo.ok(topic1);
    }


    /****************************************     groupon ----- 团购管理、团购规则    ********************************************/

    @Autowired
    GrouponServiceImpl grouponService;
    @Autowired
    GrouponRulesServiceImpl grouponRulesService;

    @GetMapping("groupon/list")
    public BaseRespVo IssueGroupon(GrouponRulesBo groupon){
        GrouponRulesVO grouponVo = grouponRulesService.queryGroupon(groupon);
        return BaseRespVo.ok(grouponVo);
    }

    @GetMapping("groupon/listRecord")
    public BaseRespVo IssueGrouponRules(GrouponRulesBo groupon){
        GrouponVo grouponVO = grouponService.queryGroupon(groupon);
        return BaseRespVo.ok(grouponVO);
    }

    @PostMapping("groupon/update")
    public BaseRespVo updateGroupon(@RequestBody Groupon groupon){ //增加RequestBody ，响应的是json数据，不再是Model And View
        int i = grouponService.updateGrouponById(groupon);
        if(i==0)return null;
        return BaseRespVo.ok();
    }

    @PostMapping("groupon/delete")
    public BaseRespVo deleteGroupon(@RequestBody Groupon groupon){
        int i = grouponService.deleteGrouponById(groupon);
        if(i==0)return null;
        return BaseRespVo.ok();
    }

   /* @PostMapping("groupon/create")
    public BaseRespVo createGroupon(@RequestBody GrouponRules rules){
        //先查找一下有没有此goodsId

        grouponRulesService.insertGrouponrule(rules);

    }*/

    /*******************************************    文件上传      ****************************************/

    @Autowired
    StorageServiceImpl storageService;


//    @PostMapping("storage/create")
//    public BaseRespVo createFile(MultipartFile file){
//        //文件存储起来
//        String newFlieName = UploadUtil2.fileUpload(file);
//        Storage storage = storageService.createFile(file,newFlieName);
//        return BaseRespVo.ok(storage);
//    }

}
