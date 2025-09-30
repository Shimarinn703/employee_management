package com.jhc.employee_management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jhc.employee_management.entity.Employee;

/**
* @author 30839
* @description 针对表【employee(社員テーブル)】的数据库操作Mapper
* @createDate 2025-07-06 19:28:49
* @Entity com.jhc.employee_management.entity.Employee
*/
public interface EmployeeMapper extends BaseMapper<Employee> {
	Employee selectById(long employeeId);
	
	Employee selectEmployeeById(long employeeId);
	
	/**
	 * 複数条件で社員を検索する
	 */
	List<Map<String, Object>> searchEmployees(@Param("params") Map<String, Object> params);
	
	/**
	 * 社員IDで社員の詳細情報を取得する
	 */
	Map<String, Object> getEmployeeDetail(@Param("id") Long id);
}
