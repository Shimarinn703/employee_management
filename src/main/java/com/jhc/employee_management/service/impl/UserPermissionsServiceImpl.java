package com.jhc.employee_management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhc.employee_management.entity.UserPermissions;
import com.jhc.employee_management.service.UserPermissionsService;
import com.jhc.employee_management.mapper.UserPermissionsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 30839
* @description 针对表【user_permissions(ユーザー権限テーブル)】的数据库操作Service实现
* @createDate 2025-07-06 19:28:49
*/
@Service
public class UserPermissionsServiceImpl extends ServiceImpl<UserPermissionsMapper, UserPermissions>
    implements UserPermissionsService{

    @Resource
    private UserPermissionsMapper userPermissionsMapper;

    @Override
    public UserPermissions getByEmployeeId(String employeeId) {
        QueryWrapper<UserPermissions> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("employee_id", employeeId);
        return userPermissionsMapper.selectOne(queryWrapper);
    }
}




