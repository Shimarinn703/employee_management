package com.jhc.employee_management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jhc.employee_management.entity.Staffbasicinfo;

/**
* @author 30839
* @description 针对表【staffbasicinfo(職員基本情報)】的数据库操作Service
* @createDate 2025-07-06 19:28:49
*/
public interface StaffbasicinfoService extends IService<Staffbasicinfo> {

	Staffbasicinfo getbyEmployeeId(long employeeId);

	void updateByEmployeeId(Staffbasicinfo staffbasicinfo) ;
}
