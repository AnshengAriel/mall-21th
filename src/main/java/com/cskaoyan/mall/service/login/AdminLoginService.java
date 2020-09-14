package com.cskaoyan.mall.service.login;

import com.cskaoyan.mall.bean.login.AdminBean;
import com.cskaoyan.mall.bean.login.InfoBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AdminLoginService {

    AdminBean queryAdmin(Map map);

    InfoBean queryAdminInfo();

    InfoBean queryByName(String username);

    List<String> queryRolenameById(List<Integer> role_ids);

    List<String> queryPermissionById(List<Integer> role_ids);
}
