package com.jhc.employee_management.controller;

import com.jhc.employee_management.entity.UserLoginInfo;
import com.jhc.employee_management.service.UserLoginInfoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/demo/user")
public class DemoController {

    @Resource
    private UserLoginInfoService userLoginInfoService;

    @RequestMapping("/{id}")
    public UserLoginInfo getUser(@PathVariable Long id) {
        return userLoginInfoService.getById(id);
    }

    @RequestMapping("/insert")
    public Integer insertUser() {
        UserLoginInfo userLoginInfo = new UserLoginInfo();
        userLoginInfo.setEmployeeId("12345678901234567291");
        userLoginInfo.setUsername("12345678901234567291");
        userLoginInfo.setPassword("123456");
        return userLoginInfoService.insertInfo(userLoginInfo);
    }

}
