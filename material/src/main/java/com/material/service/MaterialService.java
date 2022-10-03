package com.material.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.material.bean.Material;
import com.material.bean.infomation.MaterialForm;

import java.util.List;

/**
 * 物资表的service层
 * author dyq
 */
public interface MaterialService extends IService<Material> {
    //分页查询
    Page<Material> getAllMaterial(Integer current, Integer size);
    //添加
    void addMaterial(List<Material> materialList);
    //按条件分页查询
    Page<Material> getMaterialBy(Integer current, Integer size, MaterialForm materialForm);
    //查询全部物资的名称和数量
    Object getName();
    //获取pie的数据
    Object getPie();
}
