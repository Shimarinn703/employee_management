package com.jhc.employee_management.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 学歴情報
 * @TableName staffeducation
 */
@TableName(value ="staffeducation")
@Data
public class Staffeducation implements Serializable {
    /**
     * 順番
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 社員コード
     */
    @TableField(value = "employee_id")
    private Long employeeId;
    
    /**
     * 入学年月日
     */
    @TableField(value = "start_ymd")
    private Date startYmd;
    
    /**
     * 卒業年月日
     */
    @TableField(value = "end_ymd")
    private Date endYmd;
    
    /**
     * 卒業学校
     */
    @TableField(value = "school_name")
    private String schoolName;
    
    /**
    * 学部名称
    */
    @TableField(value = "faculty_name")
   private String facultyName;

   /**
    * 学科名称
    */
    @TableField(value = "department")
   private String department;

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

    /**
     * 
     */
    @TableField(value = "add1")
    private String add1;

    /**
     * 
     */
    @TableField(value = "add2")
    private String add2;

    /**
     * 
     */
    @TableField(value = "add3")
    private String add3;

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
        Staffeducation other = (Staffeducation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEmployeeId() == null ? other.getEmployeeId() == null : this.getEmployeeId().equals(other.getEmployeeId()))
            && (this.getSchoolName() == null ? other.getSchoolName() == null : this.getSchoolName().equals(other.getSchoolName()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getAdd1() == null ? other.getAdd1() == null : this.getAdd1().equals(other.getAdd1()))
            && (this.getAdd2() == null ? other.getAdd2() == null : this.getAdd2().equals(other.getAdd2()))
            && (this.getAdd3() == null ? other.getAdd3() == null : this.getAdd3().equals(other.getAdd3()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEmployeeId() == null) ? 0 : getEmployeeId().hashCode());
        result = prime * result + ((getSchoolName() == null) ? 0 : getSchoolName().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getAdd1() == null) ? 0 : getAdd1().hashCode());
        result = prime * result + ((getAdd2() == null) ? 0 : getAdd2().hashCode());
        result = prime * result + ((getAdd3() == null) ? 0 : getAdd3().hashCode());
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
        sb.append(", schoolName=").append(schoolName);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", add1=").append(add1);
        sb.append(", add2=").append(add2);
        sb.append(", add3=").append(add3);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}