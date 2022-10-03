package com.material.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.material.bean.Menu;
import com.material.mapper.MenuMapper;
import com.material.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单service层
 * author dyq
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 获取全部菜单
     * @return
     */
    @Override
    public List<Menu> getMenu(Integer id) {
        //查询全部菜单
        List<Menu> menuList = menuMapper.getMenuByUser(id);
        //循环菜单
        for (int i = 0; i < menuList.size(); i++) {
            //如果菜单的pid=0
            if (menuList.get(i).getPid() == 0){
                List<Menu> childrenList = new ArrayList<>();
                //再循环菜单
                for (int j = i + 1; j < menuList.size(); j++) {
                    //如果 菜单i的id = 菜单j的pid  就添加到childrenList里
                    if (menuList.get(i).getMenuId() == menuList.get(j).getPid()){
                        childrenList.add(menuList.get(j));
                    }
                }
                //把childrenList 赋值给菜单i的 children
                menuList.get(i).setChildren(childrenList);
                //如果childrenList是空集合，就置为null
                if (menuList.get(i).getChildren().size() == 0){
                    menuList.get(i).setChildren(null);
                }
            }
            //如果菜单的pid不是0，说明是子节点 就把它去掉
            if (menuList.get(i).getPid() != 0){
                menuList.remove(i);
                i--;
            }
        }

        return menuList;
    }
}
