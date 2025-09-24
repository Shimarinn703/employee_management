package com.jhc.employee_management.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@TableName(value ="learning_resources")
@Data
public class LearningResources {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField(value = "title")
    private String title;
    @TableField(value = "link")
    private String link;
    @TableField(value = "creator_id")
    private Long creatorId;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Tokyo")
    @TableField(value = "created_at")
    private Date createdAt;
    @TableField(value = "updated_at")
    private Date updatedAt;
    /**
     * ユーザー名
     */
    @TableField(exist = false)
    private String creatorName;
}
