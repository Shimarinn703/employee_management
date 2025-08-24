package com.jhc.employee_management.controller;

import com.jhc.employee_management.common.ApiResponse;
import com.jhc.employee_management.dto.LearningRequest;
import com.jhc.employee_management.service.LearningResourcesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/learning")
public class LearningController {

    @Resource
    LearningResourcesService learningResourcesService;

    @PostMapping("/creat")
    public ResponseEntity<?> creat(@RequestBody LearningRequest LearningRequest) {
        log.info("【creat】创建学习资料，用户名：{}", LearningRequest.getUsername());
        learningResourcesService.creatLearning(LearningRequest);
        log.info("【LOGIN】创建成功，用户名：{}", LearningRequest.getUsername());
        return ResponseEntity.ok(ApiResponse.success("creat成功", null));
    }


}
