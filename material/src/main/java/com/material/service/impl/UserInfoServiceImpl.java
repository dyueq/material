package com.material.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.material.bean.infomation.LoginUser;
import com.material.bean.UserInfo;
import com.material.bean.UserInfoRole;
import com.material.bean.infomation.UserInfoForm;
import com.material.config.Result;
import com.material.mapper.UserInfoMapper;
import com.material.mapper.UserInfoRoleMapper;
import com.material.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 用户信息表的service层
 * author dyq
 *
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserInfoRoleMapper userInfoRoleMapper;

    /**
     * 查询全部用户
     * @return
     */
    @Override
    public List<UserInfo> getAllUser() {

        return userInfoMapper.selectList(null);
    }

    /**
     * 分页查询全部用户
     * @param current 当前页
     * @param size 每页条数
     * @return
     */
    @Override
    public Page<UserInfo> getUserPage(Integer current,Integer size) {
        Page<UserInfo> page = new Page<>(current, size);
        return userInfoMapper.selectPage(page,null);
    }

    /**
     * 用户登录(shiro)
     * @param phone 用户输入的手机号码
     * @return
     */
    @Override
    public UserInfo loginUser(String phone) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_phone",phone);
        return userInfoMapper.selectOne(queryWrapper);
    }

    /**
     * 注册用户
     * @param userInfo 用户信息
     * @return
     */
    @Transactional
    @Override
    public Result register(UserInfo userInfo) {
        //判断用户名是否被注册
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userInfo.getUserName());
        UserInfo info = userInfoMapper.selectOne(queryWrapper);
        if (info==null){
            //判断身份证号是否被注册
            QueryWrapper<UserInfo> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("user_idcard",userInfo.getUserIdcard());
            UserInfo userInfo1 = userInfoMapper.selectOne(queryWrapper1);
            if (userInfo1 == null){
                //判断手机号码是否被注册
                QueryWrapper<UserInfo> queryWrapper2 = new QueryWrapper<>();
                queryWrapper2.eq("user_phone",userInfo.getUserPhone());
                UserInfo userInfo2 = userInfoMapper.selectOne(queryWrapper2);
                if (userInfo2==null){
                    //判断邮箱是否被注册
                    QueryWrapper<UserInfo> queryWrapper3 = new QueryWrapper<>();
                    queryWrapper3.eq("user_email",userInfo.getUserEmail());
                    UserInfo userInfo3 = userInfoMapper.selectOne(queryWrapper3);
                    if (userInfo3 == null){
                        userInfo.setUserDisable(0);
                        userInfo.setUserPassword(DigestUtils.md5DigestAsHex(userInfo.getUserPassword().getBytes(StandardCharsets.UTF_8)));
                        //添加用户
                        userInfoMapper.insert(userInfo);

                        UserInfoRole userInfoRole = new UserInfoRole();
                        userInfoRole.setUrRid(2);
                        userInfoRole.setUrUid(userInfo.getUserId());
                        //添加用户角色
                        userInfoRoleMapper.insert(userInfoRole);
                        return new Result(true,"注册成功，可以去登录了");
                    }
                    return new Result(false,"该邮箱已被注册");
                }
                return new Result(false,"该手机号码已被注册");
            }
            return new Result(false,"该身份证号已被注册");
        }
        return new Result(false,"该用户名已被注册");
    }

    /**
     * 登录
     * @param loginUser 暂存用户登录时的信息
     */
    @Override
    public UserInfo login(LoginUser loginUser) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_phone", loginUser.getPhone());
        return userInfoMapper.selectOne(queryWrapper);
    }

    /**
     * 按条件查找用户
     * @param current 当前页
     * @param size 每页条数
     * @param userInfoForm 暂存查询信息
     * @return
     */
    @Override
    public Page<UserInfo> getUserBy(Integer current, Integer size, UserInfoForm userInfoForm) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(userInfoForm.getUserName()),"user_name",userInfoForm.getUserName())
                .eq(StringUtils.isNotBlank(userInfoForm.getUserIdcard()),"user_idcard",userInfoForm.getUserIdcard())
                .eq(StringUtils.isNotBlank(userInfoForm.getUserPhone()),"user_phone",userInfoForm.getUserPhone())
                .eq(StringUtils.isNotBlank(userInfoForm.getUserDisable()),"user_disable",userInfoForm.getUserDisable());
        Page<UserInfo> page = new Page<>(current,size);
        return userInfoMapper.selectPage(page,queryWrapper);
    }

}
