package com.cskaoyan.mall.config;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class CustomSessionManager extends DefaultWebSessionManager {
    @Override
    protected Serializable getSessionId(ServletRequest srequest, ServletResponse response) {

        HttpServletRequest request = (HttpServletRequest) srequest;

        String sessionId1 = request.getHeader("X-cskaoyan-mall-Admin-Token");
        if(sessionId1!=null&&!"".equals(sessionId1))return sessionId1;

        String sessionId2 = request.getHeader("X-cskaoyan-mall-Wx-Token");
        if(sessionId2!=null&&!"".equals(sessionId2))return sessionId2;

        return super.getSessionId(request, response);
        //X-Litemall-Admin-Token
        //X-cskaoyan-mall-Admin-token
    }
}
