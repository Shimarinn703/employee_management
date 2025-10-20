package com.jhc.employee_management.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 反応エンティティ（postReaction）
 */
@TableName("postreaction")
public class PostReaction {
	@TableId(value = "postReaction_id", type = IdType.AUTO)
	private Long id;

	@TableField("post_id")
	private Long postId;

	// 固定 'L' 表示点赞，如后续有其他类型可扩展
	@TableField("reaction")
	private String reaction;

	@TableField("created_at")
	private Date createdAt;

	@TableField("updated_at")
	private Date updatedAt;

	// getters / setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getReaction() {
		return reaction;
	}

	public void setReaction(String reaction) {
		this.reaction = reaction;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}