package com.jhc.employee_management.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 部署テーブル
 * @TableName department
 */
@TableName(value ="department")
@Data
public class Department implements Serializable {
    /**
     * 部署ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 支店ID
     */
    @TableField(value = "branch_id")
    private Long branchId;

    /**
     * 部署名
     */
    @TableField(value = "name")
    private String name;

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
        Department other = (Department) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBranchId() == null ? other.getBranchId() == null : this.getBranchId().equals(other.getBranchId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
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
        result = prime * result + ((getBranchId() == null) ? 0 : getBranchId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
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
        sb.append(", branchId=").append(branchId);
        sb.append(", name=").append(name);
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