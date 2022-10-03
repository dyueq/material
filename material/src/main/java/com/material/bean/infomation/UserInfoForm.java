package com.material.bean.infomation;

/**
 * 管理员查询用户信息时暂存查询信息
 * author dyq
 */
public class UserInfoForm {
    private String userName;
    private String userIdcard;
    private String userPhone;
    private String userDisable;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIdcard() {
        return userIdcard;
    }

    public void setUserIdcard(String userIdcard) {
        this.userIdcard = userIdcard;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserDisable() {
        return userDisable;
    }

    public void setUserDisable(String userDisable) {
        this.userDisable = userDisable;
    }
}
