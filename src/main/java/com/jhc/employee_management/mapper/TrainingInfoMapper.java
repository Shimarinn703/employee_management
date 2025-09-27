package com.jhc.employee_management.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jhc.employee_management.entity.TrainingInfo;

/**
* @author 30839
* @description 针对表【training_info(研修情報テーブル)】的数据库操作Mapper
* @createDate 2025-09-27 19:28:49
* @Entity com.jhc.employee_management.entity.TrainingInfo
*/
public interface TrainingInfoMapper extends BaseMapper<TrainingInfo> {
    List<TrainingInfo> selectByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);
    Long countAll();
}

