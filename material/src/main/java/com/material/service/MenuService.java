package com.material.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.material.bean.Menu;

import java.util.List;

/**
 * 菜单service层接口
 * author dyq
 */
public interface MenuService extends IService<Menu> {
    List<Menu> getMenu(Integer id);
}
