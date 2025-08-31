package com.jhc.employee_management.dto;

import lombok.Data;

@Data
public class StaffSkillRequest {
	
	//**  スキルid
    private Long skillId;
    
	//**  スキル名
    private String name;
    
    //**  経験レベル
    private Integer level;
}
