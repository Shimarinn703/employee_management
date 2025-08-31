package com.jhc.employee_management.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhc.employee_management.entity.Staffbasicinfo;
import com.jhc.employee_management.mapper.StaffbasicinfoMapper;
import com.jhc.employee_management.service.StaffbasicinfoService;

/**
* @author 30839
* @description 针对表【staffbasicinfo(職員基本情報)】的数据库操作Service实现
* @createDate 2025-07-06 19:28:49
*/
@Service
public class StaffbasicinfoServiceImpl extends ServiceImpl<StaffbasicinfoMapper, Staffbasicinfo>
    implements StaffbasicinfoService{
	
	@Resource
	private StaffbasicinfoMapper staffbasicinfoMapper;
	
	@Override
	public Staffbasicinfo getbyEmployeeId(long employeeId) {
		return staffbasicinfoMapper.selectByEmployeeId(employeeId);
		
	}
	
	@Override
	public void updateByEmployeeId(Staffbasicinfo staffbasicinfo) {
		staffbasicinfoMapper.updateByEmployeeId(staffbasicinfo);
		
	}
}




