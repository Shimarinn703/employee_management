package com.jhc.employee_management.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jhc.employee_management.entity.Department;

/**
* @author 30839
* @description 针对表【department(部署テーブル)】的数据库操作Service
* @createDate 2025-07-06 19:28:49
*/
public interface DepartmentService extends IService<Department> {
	List<Department> getAllData();
}
