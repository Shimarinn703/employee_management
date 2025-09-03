package com.jhc.employee_management.dto;

import lombok.Data;

@Data
public class StaffCategoryRequest {
	//**  技術資格名
    private String categoryName;
    
    //**  取得年月日
    private String getYmd;
}
