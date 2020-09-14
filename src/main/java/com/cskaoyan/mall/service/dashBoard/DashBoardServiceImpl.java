package com.cskaoyan.mall.service.dashBoard;

import com.cskaoyan.mall.bean.dashBoard.DashBoardBean;
import com.cskaoyan.mall.mapper.dashBoard.DashBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DashBoardServiceImpl implements DashBoardService {
    
    @Autowired
    DashBoardMapper dashBoardMapper;

    @Override
    public DashBoardBean countDashBoard() {
        return dashBoardMapper.countDashBoard();
    }

    @Override
    public int updatePasswordByName(String username,String oldPassword,String newPassword) {
        if (dashBoardMapper.selectOldPasswordByName(username,oldPassword)!=null)
       return dashBoardMapper.updatePasswordByName(username,newPassword);
        return -1;
    }
}
