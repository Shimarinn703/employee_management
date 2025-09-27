package com.jhc.employee_management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jhc.employee_management.dto.PageResult;
import com.jhc.employee_management.entity.SkillQuestion;

/**
* @author 30839
* @description 针对表【skill_question to skill_answer(スキル問題と回答)】的数据库操作Service
* @createDate 2025-09-26 19:28:49
*/
public interface SkillQAService extends IService<SkillQuestion> {

	PageResult<SkillQuestion> getAllQA(int page, int size);

	SkillQuestion createQuestion(SkillQuestion skillQuestion);
    Long countQuestions();
}
