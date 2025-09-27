package com.jhc.employee_management.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


/**
 * 研修情報
 * @TableName training_info 
 */
@TableName(value ="training_info")
@Data
public class TrainingInfo implements Serializable {
	
    /**
     * 研修ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 研修内容
     */
    @TableField(value = "title")
    private String title;

    /**
     * 問題内容
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @TableField(value = "training_date")
    private LocalDate  trainingDate;

    /**
     * 講師
     */
    @TableField(value = "instructor")
    private String instructor;

    /**
     * 場所
     */
    @TableField(value = "location")
    private String location;

    /**
     * 詳細情報
     */
    @TableField(value = "description")
    private String description;
    
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
     * 作成日時
     */
    @TableField(value = "created_at")
    private Date createdAt;

    /**
     * 更新日時
     */
    @TableField(value = "updated_at")
    private Date updatedAt;

}