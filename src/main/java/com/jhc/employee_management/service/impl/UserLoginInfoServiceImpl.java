package com.jhc.employee_management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhc.employee_management.entity.UserLoginInfo;
import com.jhc.employee_management.service.UserLoginInfoService;
import com.jhc.employee_management.mapper.UserLoginInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 30839
* @description 针对表【user_login_info(ログイン情報)】的数据库操作Service实现
* @createDate 2025-07-06 19:28:49
*/
@Service
public class UserLoginInfoServiceImpl extends ServiceImpl<UserLoginInfoMapper, UserLoginInfo>
    implements UserLoginInfoService{

    @Resource
    private UserLoginInfoMapper userLoginInfoMapper;

    @Override
    public Integer insertInfo(UserLoginInfo userLoginInfo) {
        return userLoginInfoMapper.insert(userLoginInfo);
    }
}




