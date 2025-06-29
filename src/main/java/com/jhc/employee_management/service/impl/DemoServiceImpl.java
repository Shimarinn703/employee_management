package com.jhc.employee_management.service.impl;

import com.jhc.employee_management.entity.UserLoginInfo;
import com.jhc.employee_management.mapper.DemoMapper;
import com.jhc.employee_management.service.DemoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DemoServiceImpl implements DemoService {
    @Resource
    private DemoMapper demoMapper;


    @Override
    public UserLoginInfo getById(Long id) {
        return demoMapper.selectById(id);
    }

    @Override
    public Integer insertInfo(UserLoginInfo userLoginInfo) {
        return demoMapper.insert(userLoginInfo);
    }
}
