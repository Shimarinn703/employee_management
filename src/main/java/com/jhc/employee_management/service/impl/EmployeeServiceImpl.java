package com.jhc.employee_management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhc.employee_management.entity.Employee;
import com.jhc.employee_management.service.EmployeeService;
import com.jhc.employee_management.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

/**
* @author 30839
* @description 针对表【employee(社員テーブル)】的数据库操作Service实现
* @createDate 2025-07-06 19:28:49
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService{

}




