package com.jhc.employee_management.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jhc.employee_management.entity.LearningResources;
//0918whm:分页查询修改
public interface LearningResourcesMapper {
    int creatLearning(LearningResources resource);
    List<LearningResources> selectByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);
    int countAll();
}