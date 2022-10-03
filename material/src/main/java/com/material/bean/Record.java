package com.material.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

/**
 * 捐赠记录表实体类
 * author dyq
 */
public class Record {
    //id
    @TableId
    private Integer recordId;
    //用户名
    private String recordName;
    //物资名
    private String recordGoods;
    //物资数量
    private Integer recordTotal;
    //物资单位
    private String recordUnit;
    //物资类别
    private String recordType;
    //捐赠时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp recordDate;
    //审核状态
    private String recordCheck;
    //捐赠地址
    private String recordAddress;
    //捐赠备注
    private String recordRemark;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public String getRecordGoods() {
        return recordGoods;
    }

    public void setRecordGoods(String recordGoods) {
        this.recordGoods = recordGoods;
    }

    public Integer getRecordTotal() {
        return recordTotal;
    }

    public void setRecordTotal(Integer recordTotal) {
        this.recordTotal = recordTotal;
    }

    public String getRecordUnit() {
        return recordUnit;
    }

    public void setRecordUnit(String recordUnit) {
        this.recordUnit = recordUnit;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public Timestamp getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Timestamp recordDate) {
        this.recordDate = recordDate;
    }

    public String getRecordCheck() {
        return recordCheck;
    }

    public void setRecordCheck(String recordCheck) {
        this.recordCheck = recordCheck;
    }

    public String getRecordAddress() {
        return recordAddress;
    }

    public void setRecordAddress(String recordAddress) {
        this.recordAddress = recordAddress;
    }

    public String getRecordRemark() {
        return recordRemark;
    }

    public void setRecordRemark(String recordRemark) {
        this.recordRemark = recordRemark;
    }
}
