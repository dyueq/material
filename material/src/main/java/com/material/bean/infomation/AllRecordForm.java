package com.material.bean.infomation;

/**
 * 按条件查询全部捐赠记录暂存条件
 * author dyq
 */
public class AllRecordForm {
    private String userName;
    private String goods;
    private String type;
    private String check;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }
}
