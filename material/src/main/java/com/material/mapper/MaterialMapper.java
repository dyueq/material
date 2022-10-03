package com.material.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.bean.Material;
import org.apache.ibatis.annotations.Mapper;

/**
 * 物资详情表
 * author dyq
 */
@Mapper
public interface MaterialMapper extends BaseMapper<Material> {
}
