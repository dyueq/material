package com.material.bean;

import com.baomidou.mybatisplus.annotation.TableId;

/**
 * 用户信息表实体类
 * author dyq
 */
public class UserInfo {
    @TableId
    private Integer UserId;
    private String UserName;
    private String UserIdcard;
    private String UserPhone;
    private String UserEmail;
    private String UserPassword;
    private Integer userDisable;

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserIdcard() {
        return UserIdcard;
    }

    public void setUserIdcard(String userIdcard) {
        UserIdcard = userIdcard;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public Integer getUserDisable() {
        return userDisable;
    }

    public void setUserDisable(Integer userDisable) {
        this.userDisable = userDisable;
    }
}
