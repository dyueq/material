package com.material.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.material.bean.UserInfoRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色关系mapper层
 * author dyq
 */
@Mapper
public interface UserInfoRoleMapper extends BaseMapper<UserInfoRole> {
}
