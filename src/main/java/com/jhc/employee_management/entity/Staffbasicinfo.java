package com.jhc.employee_management.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 職員基本情報
 * @TableName staffbasicinfo
 */
@TableName(value ="staffbasicinfo")
@Data
public class Staffbasicinfo implements Serializable {
    /**
     * 順番
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 社員ID
     */
    @TableField(value = "employee_id")
    private Long employeeId;

    /**
     * 生年月日
     */
    @TableField(value = "birthday")
    private Date birthday;

    /**
     * 性別
     */
    @TableField(value = "gender")
    private String gender;

    /**
     * 国籍
     */
    @TableField(value = "nationality")
    private String nationality;

    /**
     * 婚姻状況
     */
    @TableField(value = "marital_status")
    private String maritalStatus;

    /**
     * 写真
     */
    @TableField(value = "photo")
    private String photo;
    
    /**
     * 住所
     */
    @TableField(value = "address")
    private String address;
    
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
        Staffbasicinfo other = (Staffbasicinfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEmployeeId() == null ? other.getEmployeeId() == null : this.getEmployeeId().equals(other.getEmployeeId()))
            && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getNationality() == null ? other.getNationality() == null : this.getNationality().equals(other.getNationality()))
            && (this.getMaritalStatus() == null ? other.getMaritalStatus() == null : this.getMaritalStatus().equals(other.getMaritalStatus()))
            && (this.getPhoto() == null ? other.getPhoto() == null : this.getPhoto().equals(other.getPhoto()))
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
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getNationality() == null) ? 0 : getNationality().hashCode());
        result = prime * result + ((getMaritalStatus() == null) ? 0 : getMaritalStatus().hashCode());
        result = prime * result + ((getPhoto() == null) ? 0 : getPhoto().hashCode());
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
        sb.append(", birthday=").append(birthday);
        sb.append(", gender=").append(gender);
        sb.append(", nationality=").append(nationality);
        sb.append(", maritalStatus=").append(maritalStatus);
        sb.append(", photo=").append(photo);
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