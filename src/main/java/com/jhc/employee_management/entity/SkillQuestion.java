package com.jhc.employee_management.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


/**
 * 技術スキル問題
 * @TableName skill_question 
 */
@TableName(value ="skill_question")
@Data
public class SkillQuestion implements Serializable {
    /**
     * 問題ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 問題内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 登録ユーザーID
     */
    @TableField(value = "creator_id")
    private Long creatorId;

    /**
     * 登録ユーザー名
     */
    @TableField(exist = false)
    private String creatorName;

    /**
     * 解消？
     */
    @TableField(value = "is_resolved")
    private Integer isResolved;

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

    /**
     * 回答一覧
     */
    @TableField(exist = false)
    private List<SkillAnswer> answers;

}