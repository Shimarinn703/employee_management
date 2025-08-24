package com.jhc.employee_management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhc.employee_management.dto.LearningRequest;
import com.jhc.employee_management.entity.Employee;
import com.jhc.employee_management.mapper.EmployeeMapper;
import com.jhc.employee_management.mapper.LearningResourcesMapper;
import com.jhc.employee_management.service.LearningResourcesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class LearningResourcesServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
        implements LearningResourcesService {
    @Resource
    private LearningResourcesMapper learningResourcesMapper;

    @Override
    public void creatLearning(LearningRequest learningRequest) {
        learningResourcesMapper.creatLearning(learningRequest);
    }
}
