package com.jhc.employee_management.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.jhc.employee_management.entity.PostComment;

/**
 * Exchange機能のサービスインターフェース（含评论功能）
 */
public interface ExchangeService {
    // 原有核心方法
    Long createPost(String content, List<String> imageUrls);
    Map<String, Object> list(Integer page, Integer pageSize);
    void like(Long postId);
    List<String> saveImages(String uploadDir, MultipartFile[] files);
    void deletePost(Long postId);
    
    // 新增：评论相关方法（PostComment格式统一）
    void addPostComment(Long postId, String content);
    List<PostComment> getPostCommentsByPostId(Long postId);
    void deletePostComment(Long postCommentId);
}