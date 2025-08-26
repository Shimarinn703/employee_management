package com.jhc.employee_management.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 社員プロジェクトテーブル
 * @TableName staffproject
 */
@Data
@TableName("staffproject")
public class StaffProject implements Serializable {
	
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * Primary Key ID
     */
    @TableId("project_id")
    private Long projectId;

    /**
     * Employee ID
     */
    @TableField("employee_id")
    private Long employeeId;

    /**
     * Project name
     */
    @TableField("project_name")
    private String projectName;

    /**
     * Project start date (YYMM)
     */
    @TableField("project_start")
    private String projectStart;

    /**
     * Project end date (YYMM)
     */
    @TableField("project_end")
    private String projectEnd;

    /**
     * Project role
     */
    @TableField("project_role")
    private String projectRole;
    
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
        StaffProject other = (StaffProject) that;
        return (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getEmployeeId() == null ? other.getEmployeeId() == null : this.getEmployeeId().equals(other.getEmployeeId()))
            && (this.getProjectName() == null ? other.getProjectName() == null : this.getProjectName().equals(other.getProjectName()))
            && (this.getProjectStart() == null ? other.getProjectStart() == null : this.getProjectStart().equals(other.getProjectStart()))
            && (this.getProjectEnd() == null ? other.getProjectEnd() == null : this.getProjectEnd().equals(other.getProjectEnd()))
            && (this.getProjectRole() == null ? other.getProjectRole() == null : this.getProjectRole().equals(other.getProjectRole()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getEmployeeId() == null) ? 0 : getEmployeeId().hashCode());
        result = prime * result + ((getProjectName() == null) ? 0 : getProjectName().hashCode());
        result = prime * result + ((getProjectStart() == null) ? 0 : getProjectStart().hashCode());
        result = prime * result + ((getProjectEnd() == null) ? 0 : getProjectEnd().hashCode());
        result = prime * result + ((getProjectRole() == null) ? 0 : getProjectRole().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", projectId=").append(projectId);
        sb.append(", employeeId=").append(employeeId);
        sb.append(", projectName=").append(projectName);
        sb.append(", projectStart=").append(projectStart);
        sb.append(", projectEnd=").append(projectEnd);
        sb.append(", projectRole=").append(projectRole);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}