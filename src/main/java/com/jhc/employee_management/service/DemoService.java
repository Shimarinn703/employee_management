package com.jhc.employee_management.service;

import com.jhc.employee_management.entity.UserLoginInfo;

public interface DemoService {

    UserLoginInfo getById(Long id);
    Integer insertInfo(UserLoginInfo userLoginInfo);
}
