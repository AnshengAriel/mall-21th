package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.bean_of_liuxufeng.Ad;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.AdBo;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.AdExample;
import com.cskaoyan.mall.bean.bean_of_liuxufeng.AdVo;
import com.cskaoyan.mall.mapper.AdMapper;
import com.cskaoyan.mall.service.AdService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdServiceImpl implements AdService {
    @Autowired
    AdMapper adMapper;

//    @Override
//    public AdVo queryAd(Integer page, Integer limit, String sort, String order) {
//        AdExample adExample = new AdExample();
//        adExample.setOrderByClause(sort+" "+order);
//         /*
//        在此增加其他条件
//        例如: adExample.createCriteria().andAgeBetween()
//         */
//        //执行查询之前使用分页
//        PageHelper.startPage(page,limit);
//        List<Ad> ads = adMapper.selectByExample(adExample);
//        PageInfo<Ad> pageInfo = new PageInfo<>(ads);
//        long total = pageInfo.getTotal();
//        AdVo adVo = new AdVo(total, ads);
//        return adVo;
//    }

    @Override
    public AdVo queryAd(AdBo adBo) {
        AdExample adExample = new AdExample();
        adExample.setOrderByClause(adBo.getSort()+" "+adBo.getOrder());
        if(adBo.getContent()==null)adBo.setContent("");
        if (adBo.getName()==null)adBo.setName("");
        adExample.createCriteria().andNameLike("%"+adBo.getName()+"%").andContentLike("%"+adBo.getContent()+"%");
         /*
        在此增加其他条件
        例如: adExample.createCriteria().andAgeBetween()
         */
        //执行查询之前使用分页
        PageHelper.startPage(adBo.getPage(), adBo.getLimit());
        List<Ad> ads = adMapper.selectByExample(adExample);
        PageInfo<Ad> pageInfo = new PageInfo<>(ads);
        long total = pageInfo.getTotal();
        AdVo adVo = new AdVo(total, ads);
        return adVo;
    }

    @Override
    public int updateAdById(Ad ad) {
        AdExample adExample = new AdExample();
        adExample.createCriteria().andIdEqualTo(ad.getId());
        int i = adMapper.updateByExample(ad, adExample);
        return i;
    }

    @Override
    public int deleteAdById(Ad ad) {
        AdExample adExample = new AdExample();
        adExample.createCriteria().andIdEqualTo(ad.getId());
        int i = adMapper.deleteByExample(adExample);
        return i;
    }

    @Override
    public Ad createAd(Ad ad) {
        int i = adMapper.insertSelective(ad);
        return ad;
    }
}
