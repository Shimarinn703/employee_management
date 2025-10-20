package com.jhc.employee_management.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jhc.employee_management.entity.PostImage;

/**
 * PostImageMapper
 */
@Mapper
public interface PostImageMapper extends BaseMapper<PostImage> {
	//    @Select("SELECT * FROM postImage WHERE post_id = #{postId}")
	//    List<PostImage> findByPostId(@Param("postId") Long postId);
}