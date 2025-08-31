package com.jhc.employee_management.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 社員スキルテーブル
 * @TableName staffskill
 */
@TableName(value ="StaffSkill")
@Data
public class StaffSkill implements Serializable {
    /**
     * 社員スキルID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 社員ID
     */
    @TableField(value = "employee_id")
    private Long employeeId;

    /**
     * スキルID
     */
    @TableField(value = "skill_id")
    private Long skillId;

    /**
     * スキル名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 経験レベル
     */
    @TableField(value = "experience_level")
    private Integer experienceLevel;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        StaffSkill other = (StaffSkill) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEmployeeId() == null ? other.getEmployeeId() == null : this.getEmployeeId().equals(other.getEmployeeId()))
            && (this.getSkillId() == null ? other.getSkillId() == null : this.getSkillId().equals(other.getSkillId()))
            && (this.getExperienceLevel() == null ? other.getExperienceLevel() == null : this.getExperienceLevel().equals(other.getExperienceLevel()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEmployeeId() == null) ? 0 : getEmployeeId().hashCode());
        result = prime * result + ((getSkillId() == null) ? 0 : getSkillId().hashCode());
        result = prime * result + ((getExperienceLevel() == null) ? 0 : getExperienceLevel().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", employeeId=").append(employeeId);
        sb.append(", skillId=").append(skillId);
        sb.append(", experienceLevel=").append(experienceLevel);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}