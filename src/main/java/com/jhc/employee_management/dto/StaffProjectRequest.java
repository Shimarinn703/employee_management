package com.jhc.employee_management.dto;

import lombok.Data;

@Data
public class StaffProjectRequest {
	
	//**  開始年月
    private String projectStart;
    
	//**  終了年月
    private String projectEnd;
    
	//**  プロジェクト名
    private String projectName;
    
	//**  プロジェクト役職
    private String projectRole;
    
}
