package com.jhc.employee_management.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jhc.employee_management.entity.StaffCategory;

/**
* @author 30839
* @description 针对表【StaffCategory(職員技術資格情報)】的数据库操作Service
* @createDate 2025-07-06 19:28:49
*/
public interface StaffCategoryService extends IService<StaffCategory> {

	List<StaffCategory> getbyEmployeeId(long employeeId);
	
	void removeByEmployeeId(long employeeId) ;
}