package com.jhc.employee_management.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("post")
public class Post {
	 // 1. 主键 ID（对应数据库 id 字段，自增）
    @TableId(type = IdType.AUTO)
    private Long id;
    // 2. 帖子内容（对应数据库 content 字段）
    private String content;
    // private Integer likes;
    // 3. 创建时间（对应数据库 created_at 字段，LocalDateTime 适配 DATETIME 类型）
    private LocalDateTime createdAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    //public Integer getLikes() { return likes; }
    //public void setLikes(Integer likes) { this.likes = likes; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    //public Object getImageUrls() {
		// TODO 自動生成されたメソッド・スタブ
    //	return null;
    //}
}
