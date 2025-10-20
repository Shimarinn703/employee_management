package com.jhc.employee_management.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@TableName("postComment") // 与数据库表名对应
@Data // Lombok 自动生成 getter/setter
public class PostComment {
    @TableId(type = IdType.AUTO) // 自增主键
    private Long id;
    
    private Long postId; // 关联帖子ID（对应表中 post_id）
    
    private String content; // 评论内容
    
    private LocalDateTime createdAt; // 评论时间（对应表中 created_at）
}