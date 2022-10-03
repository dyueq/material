package com.material.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息mapper层
 * author dyq
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
