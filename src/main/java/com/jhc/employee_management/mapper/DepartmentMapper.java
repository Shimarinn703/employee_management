package com.jhc.employee_management.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jhc.employee_management.entity.Department;

/**
* @author 30839
* @description 针对表【department(部署テーブル)】的数据库操作Mapper
* @createDate 2025-07-06 19:28:49
* @Entity com.jhc.employee_management.entity.Department
*/
public interface DepartmentMapper extends BaseMapper<Department> {
	List<Department> selectAllData();
}
