
package com.jhc.employee_management.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhc.employee_management.entity.StaffCategory;
import com.jhc.employee_management.mapper.StaffCategoryMapper;
import com.jhc.employee_management.service.StaffCategoryService;

/**
* @author 30839
* @description 针对表【staffbasicinfo(職員技術資格情報)】的数据库操作Service实现
* @createDate 2025-07-06 19:28:49
*/
@Service
public class StaffCategoryServiceImpl extends ServiceImpl<StaffCategoryMapper, StaffCategory>
    implements StaffCategoryService{
	
	@Resource
	private StaffCategoryMapper staffCategoryMapper;
	
	@Override
	public List<StaffCategory> getbyEmployeeId(long employeeId) {
		return staffCategoryMapper.selectByEmployeeId(employeeId);
		
	}
	
	
	@Override
	public void removeByEmployeeId(long employeeId) {
		staffCategoryMapper.deleteById(employeeId);
		
	}
}




