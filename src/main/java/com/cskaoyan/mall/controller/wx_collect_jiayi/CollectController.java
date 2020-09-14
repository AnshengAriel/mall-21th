package com.cskaoyan.mall.controller.wx_collect_jiayi;


import com.cskaoyan.mall.bean.BaseResoVo;
import com.cskaoyan.mall.bean.wx_collect_jiayi.BaseData7;
import com.cskaoyan.mall.bean.wx_collect_jiayi.BaseData8;
import com.cskaoyan.mall.mapper.wx_collect_jiayi.CollectMapper9;
import com.cskaoyan.mall.service.wx_collect_jiayi.CollectService9;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("wx")
public class CollectController {

    @Autowired
    CollectService9 collectService9;


    @RequestMapping("collect/list")
    public BaseResoVo brandList(Integer type,Integer page,Integer size){

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipals().getPrimaryPrincipal();

        Integer userId= collectService9.queryUserId(username);
        BaseData8 baseData8 = collectService9.queryCollect(type, userId, page, size);

        return BaseResoVo.ok(baseData8);
    }


    @RequestMapping("collect/addordelete")
    public BaseResoVo addordelete(@RequestBody Map map){

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipals().getPrimaryPrincipal();

        Integer userId= collectService9.queryUserId(username);

        Integer type = (Integer) map.get("type");
        Integer valueId = (Integer) map.get("valueId");
        Integer integer = collectService9.queryId(type,valueId,userId);

        if (integer==null){
            collectService9.insertCollect(userId,valueId,type);
            return BaseResoVo.ok(new BaseData7("add"));
        }
        else collectService9.deleteCollect(userId,valueId,type);
        return BaseResoVo.ok(new BaseData7("delete"));


    }
}
