package com.jhc.employee_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhc.employee_management.dto.PageResult;
import com.jhc.employee_management.entity.TrainingInfo;
import com.jhc.employee_management.mapper.TrainingInfoMapper;
import com.jhc.employee_management.service.TrainingInfoService;

/**
* @author 30839
* @description 针对表【skill_question to skill_answer(スキル問題と回答)】的数据库操作Service实现
* @createDate 2025-09-26 19:28:49
*/
@Service
public class TrainingInfoServiceImpl extends ServiceImpl<TrainingInfoMapper, TrainingInfo>
    implements TrainingInfoService{	
	
    @Autowired
    private TrainingInfoMapper trainingInfoMapper;

    @Override
    public PageResult<TrainingInfo> getAlltraining(int page, int size) {
        int offset = (page - 1) * size;
        
        List<TrainingInfo> items = trainingInfoMapper.selectByPage(offset, size);
        Long totalItems = trainingInfoMapper.countAll();
        
        return new PageResult<>(items, page, size, totalItems);
    }
    
    @Override
    public Long countAll() {
        return trainingInfoMapper.countAll();
    }

}
