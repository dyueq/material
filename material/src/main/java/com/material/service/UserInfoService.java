package com.material.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.material.bean.infomation.LoginUser;
import com.material.bean.UserInfo;
import com.material.bean.infomation.UserInfoForm;
import com.material.config.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户信息service层
 * author dyq
 */
public interface UserInfoService extends IService<UserInfo> {
    //查询全部用户
    List<UserInfo> getAllUser();
    //分页查询全部用户
    Page<UserInfo> getUserPage(Integer current,Integer size);
    //用户登录(shiro)
    UserInfo loginUser(String phone);
    //用户注册
    Result register(UserInfo userInfo);
    //用户登录
    UserInfo login(LoginUser loginUser);
    //按条件分页查询全部用户
    Page<UserInfo> getUserBy(Integer current, Integer size, UserInfoForm userInfoForm);
}
