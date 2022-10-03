package com.material.config;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 异常处理
 * author dyq
 */
@RestControllerAdvice("com.material.controller")
public class ExceptionConfig {
    //拦截所有异常信息
    @ExceptionHandler(SQLException.class)
    public Result getException(Exception e){
        //记录日志
        e.printStackTrace();
        return new Result(false,"格式错误");
    }
    @ExceptionHandler(NullPointerException.class)
    public Result getNullException(Exception e){
        //记录日志
        e.printStackTrace();
        return new Result(false,"服务器异常");
    }
}
