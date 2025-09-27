package com.jhc.employee_management.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jhc.employee_management.entity.SkillAnswer;
import com.jhc.employee_management.mapper.SkillAnswerMapper;
import com.jhc.employee_management.service.SkillAnswerService;

/**
* @author 30839
* @description 针对表【skill_question to skill_answer(スキル問題と回答)】的数据库操作Service实现
* @createDate 2025-09-26 19:28:49
*/
@Service
public class SkillAnswerServiceImpl extends ServiceImpl<SkillAnswerMapper, SkillAnswer>
    implements SkillAnswerService{	
	
	@Resource
	private SkillAnswerMapper skillAnswerMapper;
    
    
    @Override
    public SkillAnswer createSkillAnswer(SkillAnswer skillAnswer) {
    	//skillQuestion.setCreatedAt(new Date());
	   skillAnswer.setUpdatedAt(new Date());
        
	   skillAnswerMapper.insert(skillAnswer);
    	
    	return skillAnswer;
    }

}
