package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.bean_of_hanyuandong.*;
import com.cskaoyan.mall.mapper.AdminMapper;
import com.cskaoyan.mall.service.AdminService;
import com.cskaoyan.mall.utils.TypeConverter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;
    /**
     * 分页查询管理员信息
     * @param page 第几页
     * @param limit 每页条目数
     * @param sort 按照什么排序
     * @param order 排序方式
     * @return AdminListVo
     */
    @Override
    public AdminListVo queryAdmins(Integer page, Integer limit, String sort, String order) {
        //执行查询之前使用分页
        PageHelper.startPage(page,limit);
//        List<AdminVo> admins = adminMapper.selectAdmins(sort,order,null);
//        PageInfo<AdminVo> pageInfo = new PageInfo<>((admins));
//        long total = pageInfo.getTotal();
//        AdminListVo adminListVo = new AdminListVo(total, admins);
//        return adminListVo;
        AdminExample adminExample = new AdminExample();
        adminExample.setOrderByClause(sort + " " + order);
        List<Admin> admins = adminMapper.selectByExample(adminExample);

        return getAdminListVo(admins);
    }



    /**
     * 根据用户名查找管理员
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @param username
     * @return
     */
    @Override
    public AdminListVo queryAdminsByUsername(Integer page, Integer limit, String sort, String order, String username) {
        //先分页
        PageHelper.startPage(page,limit);
        AdminExample adminExample = new AdminExample();
        adminExample.setOrderByClause(sort + " " + order);
        adminExample.createCriteria().andUsernameLike("%" + username + "%");
        List<Admin> admins = adminMapper.selectByExample(adminExample);

        return getAdminListVo(admins);
    }

    @Override
    public List<Admin> queryAdminsByUsername(String username) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andUsernameEqualTo(username);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        return admins;
    }

    @Override
    public AdminCreateVo createAdmin(String username, String password, String avatar, List<Integer> roleIds) {
        Date date = new Date(System.currentTimeMillis());
        AdminCreateVo createVo = new AdminCreateVo(null, username, password, avatar, date, date, roleIds);
        Admin admin = new Admin(null, username, password, null, null, avatar, date, date, false, TypeConverter.parse(roleIds));
        adminMapper.insert(admin);
        return createVo;
    }

    @Override
    public AdminUpdateVo updateAdmin(AdminUpdateBo updateBo) {
        Date date = new Date(System.currentTimeMillis());
        //list--->String
        String s = TypeConverter.parse(updateBo.getRoleIds());
        adminMapper.updateById( updateBo.getId(),updateBo.getUsername(),updateBo.getAvatar()
                ,s,updateBo.getPassword(),date);
        AdminUpdateVo updateVo = new AdminUpdateVo(updateBo.getId(), updateBo.getUsername(), updateBo.getPassword(), updateBo.getAvatar(), date, updateBo.getRoleIds());
        return updateVo;
    }

    @Override
    public void logicDeleteAdminById(Integer id) {
        adminMapper.logicDeleteByPrimaryKey(id);
    }

    private AdminListVo getAdminListVo(List<Admin> admins) {
        Iterator<Admin> iterator = admins.iterator();
        while (iterator.hasNext()){
            Admin next = iterator.next();
            if (next.getDeleted() == true){
                iterator.remove();
            }
        }
        ArrayList<AdminVo> adminVos = new ArrayList<>();
        for (int i = 0; i < admins.size(); i++) {
            String roleIds = admins.get(i).getRoleIds();
            AdminVo adminVo = new AdminVo(admins.get(i).getId(), admins.get(i).getUsername(), admins.get(i).getAvatar(), TypeConverter.parse(admins.get(i).getRoleIds()));
            adminVos.add(adminVo);
        }
        PageInfo<AdminVo> pageInfo = new PageInfo<>(adminVos);
        long total = pageInfo.getTotal();
        AdminListVo adminListVo = new AdminListVo(total, adminVos);
        return adminListVo;
    }
}
