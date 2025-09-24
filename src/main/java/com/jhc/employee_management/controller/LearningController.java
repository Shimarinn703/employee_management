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
import com.jhc.employee_management.entity.LearningResources;
import com.jhc.employee_management.service.LearningResourcesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/learning")
public class LearningController {

    @Resource
    LearningResourcesService learningResourcesService;
    // GET: 分页获取学习资源 0918whm
    @GetMapping("/show")
    public ResponseEntity<?> getPagedResources(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
    	log.info("Received GET request for learning resources - page: {}, size: {}", page, size);
        PageResult<LearningResources> result = learningResourcesService.getPagedResources(page, size);
        log.debug("Returning {} resources", result.getTotal());
        return ResponseEntity.ok(ApiResponse.success("learning Resources情報の取得完了", result));
    }
 // POST: 追加学习资源 0918whm
    @Transactional
    @PostMapping("/add")
    public ResponseEntity<?> createResource(
            @RequestBody LearningResources resource) {
    	log.info("Received POST request to create resource: {}", resource.getTitle());
        LearningResources createdResource = learningResourcesService.createResource(resource);
        return ResponseEntity.ok(ApiResponse.success("learning Resourcesの保存完了", createdResource));
    }
}
