package com.jhc.employee_management.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhc.employee_management.dto.PageResult;
import com.jhc.employee_management.entity.LearningResources;
import com.jhc.employee_management.mapper.LearningResourcesMapper;
import com.jhc.employee_management.service.LearningResourcesService;

@Service
public class LearningResourcesServiceImpl implements LearningResourcesService {
    
    @Autowired
    private LearningResourcesMapper learningResourcesMapper;
    //0918whm：分页查询
    @Override
    public PageResult<LearningResources> getPagedResources(int page, int size) {
        int offset = (page - 1) * size;
        
        List<LearningResources> items = learningResourcesMapper.selectByPage(offset, size);
        Long totalItems = learningResourcesMapper.countAll();
        
        return new PageResult<>(items, page, size, totalItems);
    }
  //0918whm：创建数据
    @Override
    public LearningResources createResource(LearningResources resource) {
        resource.setCreatedAt(new Date());
        //resource.setUpdatedAt(new Date());
        
        learningResourcesMapper.creatLearning(resource);
        return resource;
    }
    
    @Override
    public Long countAll() {
        return learningResourcesMapper.countAll();
    }
}