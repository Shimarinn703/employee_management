package com.jhc.employee_management.controller;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhc.employee_management.common.ApiResponse;
import com.jhc.employee_management.dto.PageResult;
import com.jhc.employee_management.entity.TrainingInfo;
import com.jhc.employee_management.service.TrainingInfoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/training")
public class TrainingInfoController {

    @Resource
    TrainingInfoService trainingInfoService;
    
    // GET: 分页获取学习资源 0918whm
    @GetMapping("/show")
    public ResponseEntity<?> getPagedResources(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
    	log.info("Received GET request for training info - page: {}, size: {}", page, size);
        PageResult<TrainingInfo> result = trainingInfoService.getAlltraining(page, size);
        log.debug("Returning {} resources", result.getTotal());
        return ResponseEntity.ok(ApiResponse.success("training info情報の取得完了", result));
    }

    
    @Transactional
    @PostMapping("/add")
    public ResponseEntity<?> createQuestion(
            @RequestBody TrainingInfo trainingInfo) {
    	log.info("Received POST request to create skill question: {}", trainingInfo.getCreatorId());
    	trainingInfoService.save(trainingInfo);

        return ResponseEntity.ok(ApiResponse.success("training infoの保存完了", trainingInfo));
    }
}
