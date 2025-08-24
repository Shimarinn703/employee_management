package com.jhc.employee_management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jhc.employee_management.dto.LearningRequest;
import com.jhc.employee_management.entity.Employee;

import java.time.LocalDateTime;

public interface LearningResourcesMapper extends BaseMapper<Employee> {
    void creatLearning(LearningRequest learningRequest);
}
