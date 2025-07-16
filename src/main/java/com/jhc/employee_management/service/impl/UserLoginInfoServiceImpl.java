package com.jhc.employee_management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    @Override
    public UserLoginInfo getByUsername(String username) {
        QueryWrapper<UserLoginInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userLoginInfoMapper.selectOne(queryWrapper);
    }

    @Override
    public String generateNextEmployeeId() {
        // 查找当前最大 employee_id
        QueryWrapper<UserLoginInfo> wrapper = new QueryWrapper<>();
        wrapper.select("employee_id");
        wrapper.orderByDesc("employee_id");
        wrapper.last("LIMIT 1");

        UserLoginInfo latest = this.getOne(wrapper);

        long nextNumber = 1;
        if (latest != null && latest.getEmployeeId() != null) {
            try {
                nextNumber = Long.parseLong(latest.getEmployeeId()) + 1;
            } catch (NumberFormatException e) {
                throw new RuntimeException("employee_id 格式异常，必须为数字");
            }
        }

        // 补零到 10 位
        return String.format("%010d", nextNumber);
    }
}




