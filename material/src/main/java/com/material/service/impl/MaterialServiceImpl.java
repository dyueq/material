package com.material.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.material.bean.Material;
import com.material.bean.infomation.MaterialForm;
import com.material.mapper.MaterialMapper;
import com.material.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 物资明细
 * author dyq
 */
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    /**
     * 分页查询全部物资
     * @param current 当前页码
     * @param size 每页的条数
     * @return
     */
    @Override
    public Page<Material> getAllMaterial(Integer current, Integer size) {
        QueryWrapper<Material> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("goods","total","unit","type");
        Page<Material> page = new Page(current, size);
        return materialMapper.selectPage(page, queryWrapper);
    }

    /**
     * 批量
     * 添加物资
     * @param materialList 用户新建的捐赠记录
     */
    @Transactional
    @Override
    public void addMaterial(List<Material> materialList) {

        for (int i = 0; i < materialList.size(); i++) {
            QueryWrapper<Material> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("goods",materialList.get(i).getGoods())
                    .eq("unit",materialList.get(i).getUnit())
                    .eq("type",materialList.get(i).getType());

            //根据物资名称、单位、种类查找是否有这个物资
            Material material = materialMapper.selectOne(queryWrapper);
            //如果有这个物资 就把总数相加
            if (material != null){
                //仓库总数  material.getTotal();
                //捐赠数量  materialList.get(i).getTotal();
                material.setTotal(material.getTotal()+materialList.get(i).getTotal());
                materialMapper.updateById(material);
            //如果没有就添加物资信息
            }else{
                materialMapper.insert(materialList.get(i));
            }
        }
    }

    /**
     * 按条件分页查找物资信息
     * @param current 当前页
     * @param size 每页条数
     * @param materialForm 暂存查询信息
     * @return
     */
    @Override
    public Page<Material> getMaterialBy(Integer current, Integer size, MaterialForm materialForm) {
        QueryWrapper<Material> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("goods","total","unit","type")
                .eq(StringUtils.isNotBlank(materialForm.getGoods()),"goods",materialForm.getGoods())
                .eq(StringUtils.isNotBlank(materialForm.getType()),"type",materialForm.getType());
        Page<Material> page = new Page(current, size);
        return materialMapper.selectPage(page,queryWrapper);
    }

    /**
     * 查询全部物资的名称和数量
     * @return
     */
    @Override
    public Map<String,Object> getName() {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Material> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        queryWrapper.select("goods");
        List<Object> nameList = materialMapper.selectObjs(queryWrapper);
        QueryWrapper<Material> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.select("total");
        List<Object> totalList = materialMapper.selectObjs(queryWrapper1);
        map.put("name",nameList);
        map.put("total",totalList);
        return map;
    }

    /**
     * 获取pie的数据
     * @return
     */
    @Override
    public Object getPie() {
        QueryWrapper<Material> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("goods","total");
        return materialMapper.selectList(queryWrapper);
    }
}
