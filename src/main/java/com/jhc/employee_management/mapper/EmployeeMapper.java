package com.jhc.employee_management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jhc.employee_management.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
	Map<String, Object> getEmployeeDetail(@Param("id") Long id);
}
