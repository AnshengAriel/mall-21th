package com.cskaoyan.mall.service.login;


import com.cskaoyan.mall.bean.login.AdminBean;
import com.cskaoyan.mall.bean.login.AdminBeanExample;
import com.cskaoyan.mall.bean.login.InfoBean;
import com.cskaoyan.mall.mapper.login.AdminBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AdminLoginServiceImpl implements AdminLoginService {


    @Autowired
    AdminBeanMapper adminBeanMapper;

    @Override
    public AdminBean queryAdmin(Map map) {
        AdminBeanExample adminBeanExample = new AdminBeanExample();
        adminBeanExample.createCriteria().andUsernameEqualTo((String) map.get("username"));
        List<AdminBean> adminBeans = adminBeanMapper.selectByExample(adminBeanExample);
        if (adminBeans.size()==0)return null;
        AdminBean adminBean = adminBeans.get(0);
        if(!adminBean.getPassword().equals(map.get("password")))return null;
        return adminBeans.get(0);
    }

    @Override
    public InfoBean queryAdminInfo() {
        AdminBeanExample adminBeanExample = new AdminBeanExample();
return null;
    }

    @Override
    public InfoBean queryByName(String username) {
        return adminBeanMapper.selectByName(username);
    }

    @Override
    public List<String> queryRolenameById(List<Integer> role_ids) {
        ArrayList<String> strings = new ArrayList<>();
        for (Integer role_id : role_ids) {
           String rolename = adminBeanMapper.queryRolenameById(role_id);
           strings.add(rolename);
        }
        return strings;
    }

    @Override
    public List<String> queryPermissionById(List<Integer> role_ids) {
        ArrayList<String> strings = new ArrayList<>();
        for (Integer role_id : role_ids) {
            List<String> permission = adminBeanMapper.queryPermissionById(role_id);
            for (String s : permission) {
                strings.add(s);
            }
        }
        return strings;
    }
}
