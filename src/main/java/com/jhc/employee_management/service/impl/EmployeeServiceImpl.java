package com.jhc.employee_management.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhc.employee_management.entity.Employee;
import com.jhc.employee_management.mapper.EmployeeMapper;
import com.jhc.employee_management.service.EmployeeService;

/**
* @author 30839
* @description 针对表【employee(社員テーブル)】的数据库操作Service实现
* @createDate 2025-07-06 19:28:49
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService{
	
	@Resource
	private EmployeeMapper employeeMapper;
	
	@Override
	public Employee getbyEmployeeId(long employeeId) {
		return employeeMapper.selectById(employeeId);
		
	}
	
	@Override
	public Employee getbyEmployeeId(long employeeId) {
		return employeeMapper.selectEmployeeById(employeeId);
		
	}
	
	@Override
	public List<Map<String, Object>> searchEmployees(Map<String, Object> params) {
		return employeeMapper.searchEmployees(params);
	}
	
	@Override
	public Map<String, Object> getEmployeeDetail(Long id) {
		return employeeMapper.getEmployeeDetail(id);
	}
	
}




