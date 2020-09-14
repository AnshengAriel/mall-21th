package com.cskaoyan.mall.controller.wx_address_jiayi;

import com.cskaoyan.mall.bean.BaseResoVo;

import com.cskaoyan.mall.bean.login.Base;
import com.cskaoyan.mall.bean.statistic.UserBean;
import com.cskaoyan.mall.bean.statistic.UserStatisticBean;
import com.cskaoyan.mall.bean.wx_address_jiayi.*;
import com.cskaoyan.mall.service.wx_address_jiayi.AddressService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("wx")
public class AddressController {

    @Autowired
    AddressService addressService;

    @RequestMapping("address/list")
    public BaseResoVo addressList(){

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipals().getPrimaryPrincipal();
        List<Address> addresses1 = addressService.queryAddress(username);
        ArrayList<Address> addresses2 = new ArrayList<>();
        for (Address address : addresses1) {
            Address address1 = new Address();
            address1.setId(address.getId());
            address1.setName(address.getName());
            address1.setMobile(address.getMobile());
            address1.setDetailedAddress(address.getDetailedAddress());
            if(address.getIs_default().equals("true"))address1.setIsDefault(true);
            else address1.setIsDefault(false);
            addresses2.add(address1);
        }
        for (Address address : addresses2) {
            Region9 region9 = addressService.queryRegion(address.getId());
            address.setDetailedAddress("🇨🇳"+region9.getProvinceName()+" "+region9.getCityName()+" "+region9.getAreaName()+" "+address.getDetailedAddress());
        }
        return BaseResoVo.ok(addresses2);

      // return "{\"errno\":0,\"data\":[{\"isDefault\":false,\"detailedAddress\":\"湖北省武汉市洪山区 花山街道软件新城2期\",\"name\":\"松哥\",\"mobile\":\"15976753826\",\"id\":5},{\"isDefault\":false,\"detailedAddress\":\"北京市市辖区东城区 123\",\"name\":\"123\",\"mobile\":\"13660103055\",\"id\":14},{\"isDefault\":true,\"detailedAddress\":\"北京市市辖区西城区 1231212\",\"name\":\"nanfeng\",\"mobile\":\"15797679181\",\"id\":27}],\"errmsg\":\"成功\"}";
    }


    @RequestMapping("address/detail")
    public BaseResoVo addressList(String id){

        AddressDetailBean addressDetailBean = addressService.queryAddDetail(id);
        if(addressDetailBean.getIs_default().equals("1"))addressDetailBean.setIsDefault(true);
        else addressDetailBean.setIsDefault(false);
        int i = Integer.parseInt(id);
        Region9 region9 = addressService.queryRegion(i);
        addressDetailBean.setProvinceName("🇨🇳"+region9.getProvinceName()+" ");
        addressDetailBean.setCityName(region9.getCityName()+" ");
        addressDetailBean.setAreaName(region9.getAreaName());
        return BaseResoVo.ok(addressDetailBean);
    }

    @RequestMapping("region/list")
    public BaseResoVo region(String pid){

        List<Region8> region8s = addressService.queryRegion8(pid);
        return BaseResoVo.ok(region8s);
    }

    @RequestMapping("address/save")
    public BaseResoVo save(@RequestBody  AddressDetailBean addressDetailBean){

        if(addressDetailBean.getIsDefault()==true) {
            addressService.updateDefault(addressDetailBean.getId());
        }

        if(addressDetailBean.getId()==0){
            Subject subject = SecurityUtils.getSubject();
            String username = (String) subject.getPrincipals().getPrimaryPrincipal();
            Integer integer = addressService.queryUserId(username);

            if(addressDetailBean.getIsDefault()==true)addressService.updateDefaultByName(username);

            addressService.insertAddress(addressDetailBean,integer);
        }else

        addressService.updateAddress(addressDetailBean);

        return BaseResoVo.ok(addressDetailBean.getId());
    }


    @RequestMapping("address/delete")
    public BaseResoVo delete(@RequestBody  DeleteBean deleteBean){

        addressService.deleteAddressById(deleteBean.getId());
        return BaseResoVo.ok();
    }
}
