package com.cskaoyan.mall.service.dashBoard;

import com.cskaoyan.mall.bean.dashBoard.DashBoardBean;

public interface DashBoardService {

    DashBoardBean countDashBoard();
    int updatePasswordByName(String username,String oldPassword,String newPassword);
    //updatePassword();
}
