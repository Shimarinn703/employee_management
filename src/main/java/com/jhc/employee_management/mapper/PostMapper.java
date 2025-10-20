package com.jhc.employee_management.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jhc.employee_management.entity.Post;

/**
 * PostMapper（Java 8 & MyBatis-Plus）
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {

//	@Select("SELECT * FROM post ORDER BY created_at DESC LIMIT #{offset}, #{size}")
//	List<Post> findPagedV2(@Param("offset") int offset, @Param("size") int size);
//
//	@Select("SELECT COUNT(*) FROM post")
//	long countAll();
//
//	@Insert("INSERT INTO post (content) VALUES (#{content})")
//	@Options(useGeneratedKeys = true, keyProperty = "id")
//	int insert(Post post);
//
//	@Insert("INSERT INTO postimage (post_id, file_url, created_at) " +
//			"VALUES (#{postId}, #{fileUrl}, NOW())")
//	int insert(@Param("postId") Long postId, @Param("fileUrl") String fileUrl);
//
//	@Select("SELECT file_url FROM postimage WHERE post_id = #{postId} ORDER BY id ASC")
//	List<String> findUrlsByPostId(@Param("postId") Long postId);
	
    // 分页（offset/size）
    @Select("SELECT * FROM post ORDER BY created_at DESC LIMIT #{offset}, #{size}")
    List<Post> findPagedV2(@Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(*) FROM post")
    long countAll();

    // 发帖拿到自增 id（你已有）
    @Insert("INSERT INTO post (content) VALUES (#{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Post post);

    // ✅ 插入图片（新增）
    @Insert("INSERT INTO postimage (post_id, file_url, created_at) VALUES (#{postId}, #{fileUrl}, NOW())")
    int insertImage(@Param("postId") long postId, @Param("fileUrl") String fileUrl);

    // ✅ 查某帖的图片 URL 列表（列表页用）
    @Select("SELECT file_url FROM postimage WHERE post_id = #{postId} ORDER BY id ASC")
    List<String> findUrlsByPostId(@Param("postId") Long postId);
}
