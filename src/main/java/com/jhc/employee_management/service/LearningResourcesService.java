package com.jhc.employee_management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jhc.employee_management.dto.LearningRequest;
import com.jhc.employee_management.entity.Employee;

public interface LearningResourcesService extends IService<Employee> {

    void creatLearning(LearningRequest learningRequest);
}
