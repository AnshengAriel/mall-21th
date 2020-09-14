package com.cskaoyan.mall.service.wx_address_jiayi;


import com.cskaoyan.mall.bean.wx_address_jiayi.Address;
import com.cskaoyan.mall.bean.wx_address_jiayi.AddressDetailBean;
import com.cskaoyan.mall.bean.wx_address_jiayi.Region8;
import com.cskaoyan.mall.bean.wx_address_jiayi.Region9;
import com.cskaoyan.mall.mapper.wx_address_jiayi.AddressMapper9;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class AddressServiceImpl implements AddressService {

   @Autowired
   AddressMapper9 addressMapper9;

    @Override
    public List<Address> queryAddress(String username) {
        return addressMapper9.selectAddress(username);
    }

    @Override
    public Region9 queryRegion(Integer addressId) {
        return addressMapper9.selectRegion(addressId);
    }

    @Override
    public AddressDetailBean queryAddDetail(String id) {
        return addressMapper9.selectAddDetail(id);
    }

    @Override
    public List<Region8> queryRegion8(String id) {
        return addressMapper9.selectRegion8(id);
    }

    @Override
    public int updateAddress(AddressDetailBean addressDetailBean) {
        return addressMapper9.updateAddress(addressDetailBean);
    }

    @Override
    public int updateDefault(Integer id) {
        return addressMapper9.updateDefault(id);
    }

    @Override
    public int insertAddress(AddressDetailBean addressDetailBean, Integer userId) {
        return  addressMapper9.insertAddress(addressDetailBean,userId);
    }

    @Override
    public Integer queryUserId(String username) {
        return addressMapper9.selectUserId(username);
    }

    @Override
    public int updateDefaultByName(String username) {
        return addressMapper9.updateDefaultByName(username);
    }

    @Override
    public int deleteAddressById(Integer id) {
        return addressMapper9.deleteAddressById(id);
    }
}
