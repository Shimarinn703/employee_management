
package com.jhc.employee_management.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jhc.employee_management.entity.StaffSkill;

/**
* @author 30839
* @description 针对表【StaffSkill (社員スキルテーブル)】的数据库操作Mapper
* @createDate 2025-08-26 19:28:49
* @Entity com.jhc.employee_management.entity.StaffSkill
*/
public interface StaffSkillMapper extends BaseMapper<StaffSkill> {

	public List<StaffSkill> selectByEmployeeId(long employeeId) ;
	
	public void updateByEmployeeId(StaffSkill staffSkill) ;

	public void deleteById(long employeeId) ;
}
