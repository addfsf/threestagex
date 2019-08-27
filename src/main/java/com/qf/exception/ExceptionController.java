package com.qf.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value= UnauthorizedException.class)
    public String handleException(HttpServletRequest request,Exception e){
        return "unauth";
    }
}
