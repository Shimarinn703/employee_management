package com.jhc.employee_management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jhc.employee_management.entity.Staffbasicinfo;

/**
* @author 30839
* @description 针对表【staffbasicinfo(職員基本情報)】的数据库操作Mapper
* @createDate 2025-07-06 19:28:49
* @Entity com.jhc.employee_management.entity.Staffbasicinfo
*/
public interface StaffbasicinfoMapper extends BaseMapper<Staffbasicinfo> {

	//**  employee_idによりデータ検索
	Staffbasicinfo selectByEmployeeId(long employeeId) ;
	
	//**  employee_idによりデータ更新
	void updateByEmployeeId(Staffbasicinfo staffbasicinfo);
}

