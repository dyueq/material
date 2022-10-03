package com.material.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.material.bean.Anno;

/**
 * 新闻公告service层接口
 * author dyq
 */
public interface AnnoService extends IService<Anno> {
    //获取全部新闻公告
    Object getAnno();
    //根据标题查找公告
    Anno searchAnnoByTitle(String annoTitle);
    //根据标题查找新闻
    Anno searchNewsByTitle(String newsTitle);
}
