package com.material.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.bean.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单mapper层
 * author dyq
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> getMenuByUser(Integer id);
}
