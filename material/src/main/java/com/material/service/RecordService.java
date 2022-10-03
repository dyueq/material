package com.material.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.material.bean.*;
import com.material.bean.infomation.AllRecordForm;
import com.material.bean.infomation.RecordForm;
import com.material.config.Result;

import java.util.List;

public interface RecordService extends IService<Record> {
    //用户的捐赠记录  添加成未审核记录
    void toCheck(List<Material> materialList, UserInfo userInfo);
    //分页查询该用户捐赠记录
    Page<Record> getUserRecords(Integer current,Integer size,String userName);
    //按条件分页查询该用户记录
    Page<Record> getUSerRecordsBy(Integer current, Integer size, RecordForm recordForm, String userName);
    //分页查询全部用户捐赠记录
    Page<Record> getAllRecord(Integer current, Integer size);
    //按条件分页查询全部用户捐赠记录
    Page<Record> getAllRecordBy(Integer current, Integer size, AllRecordForm allRecordForm);
    //分页查找全部待审核记录
    Page<Record> getUncheckRecord(Integer current, Integer size);
    //按条件分页查找待审核记录
    Page<Record> getAllowRecordBy(Integer current, Integer size, AllRecordForm allRecordForm);
    //修改订单审核状态（未审核-->已通过/未通过）
    void updateAllowOrder(Record record);
    //修改单页订单审核状态
    void allAllOrder(List<Record> recordList);
    //分页查询该管理员的发放记录
    Page<Record> getOutRecord(Integer current, Integer size,UserInfo admin);
    //管理员发放之后添加到捐出记录
    Result addOutRecord(UserInfo admin, Record record);
    //分页查询全部发放记录
    Page<Record> getAllOutRecord(Integer current, Integer size);
    //按条件分页查询全部发放记录
    Page<Record> OutRecordBy(Integer current, Integer size, Record record);
}
