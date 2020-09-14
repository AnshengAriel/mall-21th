package com.cskaoyan.mall.mapper.mapper_weixin_wangminyi;

import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.Wangminyi_Coupon;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.Wangminyi_CouponExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Wangminyi_CouponMapper {
    long countByExample(Wangminyi_CouponExample example);

    int deleteByExample(Wangminyi_CouponExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Wangminyi_Coupon record);

    int insertSelective(Wangminyi_Coupon record);

    List<Wangminyi_Coupon> selectByExample(Wangminyi_CouponExample example);

    Wangminyi_Coupon selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Wangminyi_Coupon record, @Param("example") Wangminyi_CouponExample example);

    int updateByExample(@Param("record") Wangminyi_Coupon record, @Param("example") Wangminyi_CouponExample example);

    int updateByPrimaryKeySelective(Wangminyi_Coupon record);

    int updateByPrimaryKey(Wangminyi_Coupon record);
}