package com.material.bean;

import com.baomidou.mybatisplus.annotation.TableId;

/**
 * 新闻公告表实体类
 * author dyq
 */
public class Anno {
    @TableId
    private Integer annoId;
    private String annoTitle;
    private String annoText;
    private String newsTitle;
    private String newsText;

    public Integer getAnnoId() {
        return annoId;
    }

    public void setAnnoId(Integer annoId) {
        this.annoId = annoId;
    }

    public String getAnnoTitle() {
        return annoTitle;
    }

    public void setAnnoTitle(String annoTitle) {
        this.annoTitle = annoTitle;
    }

    public String getAnnoText() {
        return annoText;
    }

    public void setAnnoText(String annoText) {
        this.annoText = annoText;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsText() {
        return newsText;
    }

    public void setNewsText(String newsText) {
        this.newsText = newsText;
    }

}
