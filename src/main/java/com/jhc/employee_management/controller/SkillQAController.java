package com.jhc.employee_management.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.jhc.employee_management.entity.SkillAnswer;
import com.jhc.employee_management.entity.SkillQuestion;
import com.jhc.employee_management.service.SkillAnswerService;
import com.jhc.employee_management.service.SkillQAService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/skillQA")
public class SkillQAController {

    @Resource
    SkillQAService skillQAService;
    
    @Resource
    SkillAnswerService skillAnswerService;
    
    // GET: 分页获取QA资源 1018whm
    @GetMapping("/show")
    public ResponseEntity<?> getPagedResources(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
    	log.info("Received GET request for skill question - page: {}, size: {}", page, size);
        
    	PageResult<SkillQuestion> result = skillQAService.getAllQA(page, size);
        
    	log.debug("Returning {} resources", result.getTotal());
        return ResponseEntity.ok(ApiResponse.success("learning Resources情報の取得完了", result));
    }
 // POST: 追加Question 1018whm
    @Transactional
    @PostMapping("/addQuestion")
    public ResponseEntity<?> createQuestion(
            @RequestBody SkillQuestion skillQuestion) {
    	log.info("Received POST request to create skill question: {}", skillQuestion.getCreatorName());
    	
    	SkillQuestion createdQa = skillQAService.createQuestion(skillQuestion);
        
    	return ResponseEntity.ok(ApiResponse.success("Questionの保存完了", createdQa));
    }
 // POST: 追加Answer 1018whm
    @Transactional
    @PostMapping("/addAnswer")
    public ResponseEntity<?> createAnswer(
            @RequestBody SkillQuestion skillQuestion) {
    	log.info("Received POST request to create skill answer: {}", skillQuestion.getCreatorName());
    	
    	SkillQuestion createdQa = (skillQuestion);
    	
    	List<SkillAnswer> listAnswer = createdQa.getAnswers();
    	
    	List<SkillAnswer> listAnswerNew = new ArrayList<SkillAnswer>();
    	
    	for(SkillAnswer ans:listAnswer) {
    		skillAnswerService.createSkillAnswer(ans);
    		listAnswerNew.add(ans);
    	}
    	createdQa.setAnswers(listAnswerNew);

        return ResponseEntity.ok(ApiResponse.success("lAnswerの保存完了", createdQa));
    }    
}
