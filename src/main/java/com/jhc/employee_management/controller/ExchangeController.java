package com.jhc.employee_management.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jhc.employee_management.entity.PostComment;
import com.jhc.employee_management.service.ExchangeService;

import lombok.Data;

/**
 * Exchange機能のRESTコントローラ（含评论功能，Java 8互換版）
 */
@RestController
@RequestMapping("/api/exchange")
public class ExchangeController {
    @Resource
    private ExchangeService exchangeService;

    @Value("${file.upload-path}")
    private String uploadDir;

    // 原有：创建帖子
    @PostMapping("/posts")
    public Map<String, Long> create(@RequestBody CreatePostReq req) {
        Long id = exchangeService.createPost(req.getContent(), req.getImageUrls());
        Map<String, Long> resp = new HashMap<>();
        resp.put("id", id);
        return resp;
    }

    // 原有：删除帖子
    @DeleteMapping("/posts/{id}")
    public Map<String, String> deletePost(@PathVariable("id") Long postId) {
        exchangeService.deletePost(postId);
        Map<String, String> result = new HashMap<>();
        result.put("msg", "投稿削除に成功しました");
        return result;
    }

    // 原有：点赞
    @PostMapping("/posts/{id}/like")
    public void like(@PathVariable Long id) {
        exchangeService.like(id);
    }

    // 原有：图片上传
    @PostMapping(value = "/upload/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, List<String>> upload(@RequestPart("files") MultipartFile[] files) {
        List<String> urls = exchangeService.saveImages(uploadDir, files);
        Map<String, List<String>> resp = new HashMap<>();
        resp.put("urls", urls);
        return resp;
    }

    // 原有：帖子列表查询
    @GetMapping("/posts")
    public Map<String, Object> list(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return exchangeService.list(page, pageSize);
    }

    // 新增：发表评论
    @PostMapping("/posts/{postId}/post-comments")
    public Map<String, String> addPostComment(
            @PathVariable Long postId,
            @RequestBody Map<String, String> req) {
        String content = req.get("content");
        exchangeService.addPostComment(postId, content);
        Map<String, String> result = new HashMap<>();
        result.put("msg", "コメント投稿に成功しました");
        return result;
    }

    // 新增：查询帖子的评论
    @GetMapping("/posts/{postId}/post-comments")
    public List<PostComment> getPostComments(@PathVariable Long postId) {
        return exchangeService.getPostCommentsByPostId(postId);
    }

    // 新增：删除评论
    @DeleteMapping("/post-comments/{postCommentId}")
    public Map<String, String> deletePostComment(@PathVariable Long postCommentId) {
        exchangeService.deletePostComment(postCommentId);
        Map<String, String> result = new HashMap<>();
        result.put("msg", "コメント削除に成功しました");
        return result;
    }

    // 原有：创建帖子的请求参数类
    @Data
    public static class CreatePostReq {
        private String content;
        private List<String> imageUrls;
    }
}