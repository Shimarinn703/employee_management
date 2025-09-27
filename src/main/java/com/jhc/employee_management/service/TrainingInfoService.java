package com.jhc.employee_management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jhc.employee_management.dto.PageResult;
import com.jhc.employee_management.entity.TrainingInfo;

/**
* @author 30839
* @description 针对表【training_info(研修情報テーブル)】的数据库操作Service
* @createDate 2025-09-26 19:28:49
*/
public interface TrainingInfoService extends IService<TrainingInfo> {
	PageResult<TrainingInfo> getAlltraining(int page,int size);
	Long countAll();
}
