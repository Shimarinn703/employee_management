package com.jhc.employee_management.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jhc.employee_management.entity.PostComment;

@Mapper
public interface PostCommentMapper extends BaseMapper<PostComment> {
    // 1. 新增评论（插入 postComment 表）
    @Insert("INSERT INTO postComment (post_id, content, created_at) " +
            "VALUES (#{postId}, #{content}, NOW())")
    int insertPostComment(@Param("postId") Long postId, @Param("content") String content);
    
    // 2. 按帖子ID查询评论（新评论在前）
    @Select("SELECT id, post_id AS postId, content, created_at AS createdAt " +
            "FROM postComment WHERE post_id = #{postId} " +
            "ORDER BY created_at DESC")
    List<PostComment> findPostCommentsByPostId(@Param("postId") Long postId);
}