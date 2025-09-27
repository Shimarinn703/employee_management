
package com.jhc.employee_management.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jhc.employee_management.entity.SkillQuestion;

/**
* @author 30839
* @description 针对表【skill_question to skill_answer(スキル問題と回答)】的数据库操作Mapper
* @createDate 2025-09-26 19:28:49
* @Entity com.jhc.employee_management.entity.Skill
*/
public interface SkillQuestionMapper extends BaseMapper<SkillQuestion> {

	List<SkillQuestion> selectPagedWithAnswers(@Param("offset") int offset, @Param("limit") int limit);
	
	Long countQuestions();
}
