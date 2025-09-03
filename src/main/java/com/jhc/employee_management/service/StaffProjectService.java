package com.jhc.employee_management.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jhc.employee_management.entity.StaffProject;

/**
* @author 30839
* @description 针对表【StaffProject(職員プロジェクト情報)】的数据库操作Service
* @createDate 2025-07-06 19:28:49
*/
public interface StaffProjectService extends IService<StaffProject> {

	List<StaffProject> getbyEmployeeId(long employeeId);
	
	void removeByEmployeeId(long employeeId) ;
}