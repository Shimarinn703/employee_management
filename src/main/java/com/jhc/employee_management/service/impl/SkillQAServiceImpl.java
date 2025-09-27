package com.jhc.employee_management.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhc.employee_management.dto.PageResult;
import com.jhc.employee_management.entity.SkillQuestion;
import com.jhc.employee_management.mapper.SkillQuestionMapper;
import com.jhc.employee_management.service.SkillQAService;

/**
* @author 30839
* @description 针对表【skill_question to skill_answer(スキル問題と回答)】的数据库操作Service实现
* @createDate 2025-09-26 19:28:49
*/
@Service
public class SkillQAServiceImpl extends ServiceImpl<SkillQuestionMapper, SkillQuestion>
    implements SkillQAService{
	
	@Resource
	private SkillQuestionMapper skillQuestionMapper;

    
    @Override
    public PageResult<SkillQuestion> getAllQA(int page, int size) {
        int offset = (page - 1) * size;
        
        List<SkillQuestion> items = skillQuestionMapper.selectPagedWithAnswers(offset, size);
        Long totalItems = skillQuestionMapper.countQuestions();
        
        return new PageResult<>(items, page, size, totalItems);
    }

    @Override
    public SkillQuestion createQuestion(SkillQuestion skillQuestion) {
    	//skillQuestion.setCreatedAt(new Date());
    	skillQuestion.setUpdatedAt(new Date());
        
    	skillQuestionMapper.insert(skillQuestion);
    	
    	return skillQuestion;
    }
    public Long countQuestions() {
        return skillQuestionMapper.countQuestions();
    }
}

