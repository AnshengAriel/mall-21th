package com.cskaoyan.mall.mapper.wx_address_jiayi;



import com.cskaoyan.mall.bean.bean_of_wangyan.Region;
import com.cskaoyan.mall.bean.wx_address_jiayi.Address;
import com.cskaoyan.mall.bean.wx_address_jiayi.AddressDetailBean;
import com.cskaoyan.mall.bean.wx_address_jiayi.Region8;
import com.cskaoyan.mall.bean.wx_address_jiayi.Region9;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressMapper9 {

    List<Address> selectAddress(@Param("username") String username);

    Region9 selectRegion(@Param("addressId") Integer addressId);

    AddressDetailBean selectAddDetail(@Param("id") String id);

    List<Region8> selectRegion8(@Param("id") String id);

    int updateAddress(@Param("addressDetailBean") AddressDetailBean addressDetailBean);

    int updateDefault(@Param("id") Integer id);

    int insertAddress(@Param("addressDetailBean") AddressDetailBean addressDetailBean, @Param("userId") Integer userId);

    Integer selectUserId(@Param("username") String username);

    int updateDefaultByName(@Param("username") String username);

    int deleteAddressById(@Param("id") Integer id);
}
