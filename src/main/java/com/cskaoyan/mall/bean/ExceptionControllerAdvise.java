package com.cskaoyan.mall.bean;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionControllerAdvise {

    @ExceptionHandler({AuthorizationException.class, IncorrectCredentialsException.class,
            AuthenticationException.class})
    @ResponseBody
    public BaseResoVo handlerAuthorException(Exception e){

        return BaseResoVo.error(605,"用户帐号或密码不正确");//ModelandView
       // return BaseResoVo.error(402,"参数值不对");//ModelandView
    }
}
