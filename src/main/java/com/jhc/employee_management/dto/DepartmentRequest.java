package com.jhc.employee_management.dto;

import lombok.Data;

@Data
public class DepartmentRequest {
	//**  支店ID
    private Long branchId;
    
    //**  支店名
    private String branchName;
	//**  部署ID
    private Long departmentId;
    
    //**  部署名
    private String departmentName;
}
