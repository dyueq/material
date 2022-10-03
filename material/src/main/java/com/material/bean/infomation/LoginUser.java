package com.material.bean.infomation;

/**
 * 用户登录时用来暂存登录信息的实体类
 * author dyq
 */
public class LoginUser {
    private String phone;
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
