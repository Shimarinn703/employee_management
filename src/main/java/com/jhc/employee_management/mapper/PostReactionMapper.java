package com.jhc.employee_management.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jhc.employee_management.entity.PostReaction;

/**
 * PostReactionMapper
 */
@Mapper
public interface PostReactionMapper extends BaseMapper<PostReaction> {
	//    @Select("SELECT COUNT(*) FROM postReaction WHERE post_id = #{postId}")
	//    long countByPostId(@Param("postId") Long postId);

	//	@Insert("INSERT INTO postreaction (post_id, reaction, created_at, updated_at) " +
	//			"VALUES (#{postId}, 'L', NOW(), NOW())")
	//	int insertLike(@Param("postId") Long postId);
	//
	//	@Select("SELECT COUNT(*) FROM postreaction WHERE post_id = #{postId} AND reaction = 'L'")
	//	int countLikes(@Param("postId") Long postId);

	// ✅ 点赞：插一条 ‘L’
	@Insert("INSERT INTO postreaction (post_id, reaction, created_at, updated_at) VALUES (#{postId}, 'L', NOW(), NOW())")
	int insertLike(@Param("postId") Long postId);

	// ✅ 统计点赞数：按 post_id + reaction='L'
	@Select("SELECT COUNT(*) FROM postreaction WHERE post_id = #{postId} AND reaction = 'L'")
	int countLikes(@Param("postId") Long postId);
}