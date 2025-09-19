package com.jhc.employee_management.service;

import com.jhc.employee_management.dto.PageResult;
import com.jhc.employee_management.entity.LearningResources;

public interface LearningResourcesService  {
    //0918whm：分页
	PageResult<LearningResources> getPagedResources(int page, int size);
    LearningResources createResource(LearningResources resource);
    int countAll();
}
