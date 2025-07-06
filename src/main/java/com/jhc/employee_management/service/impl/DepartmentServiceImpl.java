package com.jhc.employee_management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhc.employee_management.entity.Department;
import com.jhc.employee_management.service.DepartmentService;
import com.jhc.employee_management.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

/**
* @author 30839
* @description 针对表【department(部署テーブル)】的数据库操作Service实现
* @createDate 2025-07-06 19:28:49
*/
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
    implements DepartmentService{

}




