package com.jhc.employee_management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jhc.employee_management.entity.SkillAnswer;

/**
* @author 30839
* @description 针对表【skill_question to skill_answer(スキル問題と回答)】的数据库操作Service
* @createDate 2025-09-26 19:28:49
*/
public interface SkillAnswerService extends IService<SkillAnswer> {
	SkillAnswer createSkillAnswer(SkillAnswer skillAnswer);
}
