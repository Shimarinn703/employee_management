package com.jhc.employee_management.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

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
    @TableField(value = "created_at")
    private Date createdAt;
    @TableField(value = "updated_at")
    private Date updatedAt;
}
