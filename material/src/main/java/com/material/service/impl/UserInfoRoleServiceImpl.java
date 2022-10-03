package com.material.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.material.bean.UserInfoRole;
import com.material.mapper.UserInfoRoleMapper;
import com.material.service.UserInfoRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户角色关系service层
 * author dyq
 */
@Service
public class UserInfoRoleServiceImpl extends ServiceImpl<UserInfoRoleMapper, UserInfoRole> implements UserInfoRoleService {
}
