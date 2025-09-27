package com.jhc.employee_management.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


/**
 * 技術スキル問題の回答
 * @TableName skill_answer 
 */
@TableName(value ="skill_answer")
@Data
public class SkillAnswer implements Serializable {
	
    /**
     * 回答ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 問題ID
     */
    @TableField(value = "question_id")
    private Long questionId;

    /**
     * 問題内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * like_count
     */
    @TableField(value = "like_count")
    private Long likeCount;

    /**
     * 登録ユーザーID
     */
    @TableField(value = "creator_id")
    private Long creatorId;

    /**
     * 登録ユーザー名
     */
    @TableField(exist = false)
    private String creatorName;;

    /**
     * 作成日時
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Tokyo")
    @TableField(value = "created_at")
    private Date createdAt;

    /**
     * 更新日時
     */
    @TableField(value = "updated_at")
    private Date updatedAt;

}