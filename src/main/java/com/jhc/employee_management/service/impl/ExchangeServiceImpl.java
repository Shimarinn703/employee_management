package com.jhc.employee_management.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jhc.employee_management.entity.Post;
import com.jhc.employee_management.entity.PostComment;
import com.jhc.employee_management.mapper.PostCommentMapper;
import com.jhc.employee_management.mapper.PostMapper;
import com.jhc.employee_management.mapper.PostReactionMapper;
import com.jhc.employee_management.service.ExchangeService;

@Service
public class ExchangeServiceImpl implements ExchangeService {
    private static final Logger log = LoggerFactory.getLogger(ExchangeServiceImpl.class);

    // 原有Mapper注入
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private PostReactionMapper postReactionMapper;
    // 新增：PostCommentMapper注入
    @Autowired
    private PostCommentMapper postCommentMapper;

    // 原有：创建帖子
    @Override
    public Long createPost(String content, List<String> imageUrls) {
        Post post = new Post();
        post.setContent(content);
        postMapper.insert(post);
        Long postId = post.getId();
        
        if (imageUrls != null) {
            for (String url : imageUrls) {
                if (url != null && !url.isEmpty()) {
                    postMapper.insertImage(postId, url);
                }
            }
        }
        return postId;
    }

    // 原有：图片上传
    @Override
    public List<String> saveImages(String uploadDir, MultipartFile[] files) {
        try {
            Path base = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(base);
            List<String> urls = new ArrayList<>();
            
            for (MultipartFile f : files) {
                String original = Objects.requireNonNull(f.getOriginalFilename());
                String cleaned = original.replaceAll("[/\\\\]+", "_").replaceAll("\\s+", "_");
                String safe = System.currentTimeMillis() + "_" + cleaned;
                Path dst = base.resolve(safe);
                
                log.info("准备保存文件到：" + dst.toString());
                f.transferTo(dst.toFile());
                log.info("保存完成：" + dst.toString());
                
                urls.add("/uploads/" + safe);
            }
            return urls;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 原有：点赞
    @Override
    public void like(Long postId) {
        postReactionMapper.insertLike(postId);
    }

    // 原有：帖子列表查询（新增评论数据返回）
    @Override
    public Map<String, Object> list(Integer page, Integer pageSize) {
        int safePage = page == null ? 0 : Math.max(0, page);
        int safeSize = pageSize == null ? 10 : Math.max(1, pageSize);
        long total = postMapper.countAll();
        int lastPage = total == 0 ? 0 : (int)((total - 1) / safeSize);
        
        if (safePage > lastPage) safePage = lastPage;
        int offset = safePage * safeSize;
        List<Post> rows = postMapper.findPagedV2(offset, safeSize);

        // 转换为前端所需DTO（新增评论数和评论列表）
        List<Map<String, Object>> dto = rows.stream().map(p -> {
            Map<String, Object> m = new HashMap<>();
            m.put("id", p.getId());
            m.put("content", p.getContent());
            m.put("likes", postReactionMapper.countLikes(p.getId()));
            m.put("imageUrls", postMapper.findUrlsByPostId(p.getId()));
            
            // 新增：查询当前帖子的评论
            List<PostComment> postComments = postCommentMapper.findPostCommentsByPostId(p.getId());
            m.put("commentCount", postComments.size()); // 评论数
            m.put("postComments", postComments); // 评论列表（键名对齐PostComment）
            
            m.put("createdAt", p.getCreatedAt());
            return m;
        }).collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("content", dto);
        result.put("totalElements", total);
        return result;
    }

    // 原有：删除帖子
    @Override
    public void deletePost(Long postId) {
        Post post = postMapper.selectById(postId);
        if (post == null) {
            log.warn("删除失败：帖子ID {} 不存在", postId);
            throw new RuntimeException("削除する投稿が存在しません（投稿ID：" + postId + "）");
        }
        
        postMapper.deleteById(postId);
        log.info("帖子删除成功：帖子ID {}，内容：{}", postId, post.getContent());
    }

    // 新增：发表评论
    @Override
    public void addPostComment(Long postId, String content) {
        // 1. 校验帖子是否存在
        Post post = postMapper.selectById(postId);
        if (post == null) {
            log.warn("评论失败：帖子ID {} 不存在", postId);
            throw new RuntimeException("评论する投稿が存在しません");
        }
        
        // 2. 校验评论内容（1-512字）
        if (content == null || content.trim().isEmpty() || content.length() > 512) {
            throw new RuntimeException("コメントは1～512文字で入力してください");
        }
        
        // 3. 插入评论
        postCommentMapper.insertPostComment(postId, content.trim());
        log.info("帖子ID {} 新增评论：{}", postId, content.trim());
    }

    // 新增：查询帖子的评论
    @Override
    public List<PostComment> getPostCommentsByPostId(Long postId) {
        return postCommentMapper.findPostCommentsByPostId(postId);
    }

    // 新增：删除评论
    @Override
    public void deletePostComment(Long postCommentId) {
        PostComment postComment = postCommentMapper.selectById(postCommentId);
        if (postComment == null) {
            log.warn("删除评论失败：评论ID {} 不存在", postCommentId);
            throw new RuntimeException("削除するコメントが存在しません");
        }
        
        postCommentMapper.deleteById(postCommentId);
        log.info("评论ID {} 删除成功", postCommentId);
    }
}