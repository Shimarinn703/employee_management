package com.jhc.employee_management.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<PageResult<LearningResources>> getPagedResources(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
    	log.info("Received GET request for learning resources - page: {}, size: {}", page, size);
        PageResult<LearningResources> result = learningResourcesService.getPagedResources(page, size);
        log.debug("Returning {} resources", result.getItems().size());
        return ResponseEntity.ok(result);
    }
 // POST: 追加学习资源 0918whm
    @PostMapping("/add")
    public ResponseEntity<LearningResources> createResource(
            @RequestBody LearningResources resource) {
    	log.info("Received POST request to create resource: {}", resource.getTitle());
        LearningResources createdResource = learningResourcesService.createResource(resource);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdResource);
    }
}
