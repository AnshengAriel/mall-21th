package com.cskaoyan.mall.mapper.dashBoard;


import com.cskaoyan.mall.bean.dashBoard.DashBoardBean;
import org.apache.ibatis.annotations.Param;

public interface DashBoardMapper {

    DashBoardBean countDashBoard();

    int updatePasswordByName(@Param("username") String username, @Param("newPassword") String newPassword);

    Integer selectOldPasswordByName(@Param("username") String username, @Param("oldPassword") String oldPassword);
}
