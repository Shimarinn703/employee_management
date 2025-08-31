package com.jhc.employee_management.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhc.employee_management.entity.StaffSkill;
import com.jhc.employee_management.mapper.StaffSkillMapper;
import com.jhc.employee_management.service.StaffSkillService;

/**
* @author 30839
* @description 针对表【staffbasicinfo(職員基本情報)】的数据库操作Service实现
* @createDate 2025-07-06 19:28:49
*/
@Service
public class StaffSkillServiceImpl extends ServiceImpl<StaffSkillMapper, StaffSkill>
    implements StaffSkillService{
	
	@Resource
	private StaffSkillMapper staffSkillMapper;
	
	@Override
	public List<StaffSkill> getbyEmployeeId(long employeeId) {
		return staffSkillMapper.selectByEmployeeId(employeeId);
		
	}
	
	@Override
	public void updateByEmployeeId(StaffSkill staffSkill) {
		staffSkillMapper.updateByEmployeeId(staffSkill);
		
	}
	
	@Override
	public void removeByEmployeeId(long employeeId) {
		staffSkillMapper.deleteById(employeeId);
		
	}
}




