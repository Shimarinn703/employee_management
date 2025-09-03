package com.jhc.employee_management.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhc.employee_management.entity.StaffProject;
import com.jhc.employee_management.mapper.StaffProjectMapper;
import com.jhc.employee_management.service.StaffProjectService;

/**
* @author 30839
* @description 针对表【StaffProject(職員プロジェクト情報)】的数据库操作Service实现
* @createDate 2025-07-06 19:28:49
*/
@Service
public class StaffProjectServiceImpl extends ServiceImpl<StaffProjectMapper, StaffProject>
    implements StaffProjectService{
	
	@Resource
	private StaffProjectMapper staffProjectMapper;
	
	@Override
	public List<StaffProject> getbyEmployeeId(long employeeId) {
		return staffProjectMapper.selectByEmployeeId(employeeId);
		
	}
	
	
	@Override
	public void removeByEmployeeId(long employeeId) {
		staffProjectMapper.deleteById(employeeId);
		
	}
}




