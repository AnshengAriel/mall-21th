package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.bean_of_hanyuandong.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminService {

    AdminListVo queryAdmins(Integer page, Integer limit, String sort, String order);

    AdminListVo queryAdminsByUsername(Integer page, Integer limit, String sort, String order, String username);

    List<Admin> queryAdminsByUsername(String username);

    AdminCreateVo createAdmin(String username, String password, String avatar, List<Integer> roleIds);

    AdminUpdateVo updateAdmin(AdminUpdateBo updateBo);

    void logicDeleteAdminById(Integer id);
}
