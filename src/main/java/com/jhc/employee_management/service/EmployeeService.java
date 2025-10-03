package com.jhc.employee_management.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jhc.employee_management.entity.Employee;

/**
* @author 30839
* @description 针对表【employee(社員テーブル)】的数据库操作Service
* @createDate 2025-07-06 19:28:49
*/
public interface EmployeeService extends IService<Employee> {
	Employee getbyEmployeeId(long employeeId);
	
	/**
	 * 複数条件で社員を検索する（分页）
	 */
	List<Map<String, Object>> searchEmployees(Map<String, Object> params);
	
	/**
	 * 検索条件に一致する社員の総数を取得する
	 */
	long countEmployees(Map<String, Object> params);
	
	/**
	 * 社員IDで社員の詳細情報を取得する
	 */
	Map<String, Object> getEmployeeDetail(Long id);
}
