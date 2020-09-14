package com.cskaoyan.mall.service.wx_address_jiayi;


import com.cskaoyan.mall.bean.wx_address_jiayi.Address;
import com.cskaoyan.mall.bean.wx_address_jiayi.AddressDetailBean;
import com.cskaoyan.mall.bean.wx_address_jiayi.Region8;
import com.cskaoyan.mall.bean.wx_address_jiayi.Region9;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressService {

    List<Address> queryAddress(String username);

    Region9 queryRegion(Integer addressId);

    AddressDetailBean queryAddDetail(String id);

    List<Region8> queryRegion8(String id);

    int updateAddress(AddressDetailBean addressDetailBean);

    int updateDefault(Integer id);

    int insertAddress(AddressDetailBean addressDetailBean,Integer userId);

    Integer queryUserId(String username);

    int updateDefaultByName(String username);

    int deleteAddressById(Integer id);
}
