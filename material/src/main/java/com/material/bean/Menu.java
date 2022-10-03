package com.material.bean;

import com.baomidou.mybatisplus.annotation.TableField;

import java.util.List;

/**
 * 菜单表
 * author dyq
 */
public class Menu {
    private Integer menuId;
    private String menuName;
    private String menuPath;
    private String menuLabel;
    private String menuIcon;
    private Integer pid;

    @TableField(exist = false)
    private List<Menu> children;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public String getMenuLabel() {
        return menuLabel;
    }

    public void setMenuLabel(String menuLabel) {
        this.menuLabel = menuLabel;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", menuPath='" + menuPath + '\'' +
                ", menuLabel='" + menuLabel + '\'' +
                ", menuIcon='" + menuIcon + '\'' +
                ", pid=" + pid +
                ", children=" + children +
                '}';
    }
}
