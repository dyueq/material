package com.material.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.material.bean.Anno;
import com.material.mapper.AnnoMapper;
import com.material.service.AnnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 新闻公告service层
 * author dyq
 */
@Service
public class AnnoServiceImpl extends ServiceImpl<AnnoMapper, Anno> implements AnnoService {

    @Autowired
    private AnnoMapper annoMapper;

    /**
     * 查找全部新闻公告
     * @return
     */
    @Override
    public Object getAnno() {
        List<Anno> list = annoMapper.selectList(null);
        Map<String, Object> map = new HashMap<>();
        List<Anno> annoList = new ArrayList<>();
        List<Anno> newsList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAnnoTitle() != null){
                annoList.add(list.get(i));
            }else{
                newsList.add(list.get(i));
            }
        }
        map.put("annoList",annoList);
        map.put("newsList",newsList);
        return map;
    }

    /**
     * 根据标题查找公告
     * @param annoTitle 公告标题
     * @return
     */
    @Override
    public Anno searchAnnoByTitle(String annoTitle) {
        QueryWrapper<Anno> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("anno_title",annoTitle);
        return annoMapper.selectOne(queryWrapper);
    }

    /**
     * 根据标题查找新闻
     * @param newsTitle 新闻标题
     * @return
     */
    @Override
    public Anno searchNewsByTitle(String newsTitle) {
        QueryWrapper<Anno> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("news_title",newsTitle);
        return annoMapper.selectOne(queryWrapper);
    }
}
