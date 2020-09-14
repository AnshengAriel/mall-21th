package com.cskaoyan.mall.mapper.mapper_weixin_wangminyi;

import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.Wangminyi_Coupon_User;
import com.cskaoyan.mall.bean.bean_of_wangminyi.bean_weixin_wangminyi.Wangminyi_Coupon_UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Wangminyi_Coupon_UserMapper {
    long countByExample(Wangminyi_Coupon_UserExample example);

    int deleteByExample(Wangminyi_Coupon_UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Wangminyi_Coupon_User record);

    int insertSelective(Wangminyi_Coupon_User record);

    List<Wangminyi_Coupon_User> selectByExample(Wangminyi_Coupon_UserExample example);

    Wangminyi_Coupon_User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Wangminyi_Coupon_User record, @Param("example") Wangminyi_Coupon_UserExample example);

    int updateByExample(@Param("record") Wangminyi_Coupon_User record, @Param("example") Wangminyi_Coupon_UserExample example);

    int updateByPrimaryKeySelective(Wangminyi_Coupon_User record);

    int updateByPrimaryKey(Wangminyi_Coupon_User record);
}