package com.jhc.employee_management.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * パスポート情報
 * @TableName staffpassport
 */
@TableName(value ="staffpassport")
@Data
public class Staffpassport implements Serializable {
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
     * パスポート番号
     */
    @TableField(value = "passport_number")
    private String passportNumber;

    /**
     * パスポート有効期限
     */
    @TableField(value = "passport_expiry")
    private Date passportExpiry;

    /**
     * 在留資格または期間
     */
    @TableField(value = "residence_status")
    private String residenceStatus;

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
        Staffpassport other = (Staffpassport) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEmployeeId() == null ? other.getEmployeeId() == null : this.getEmployeeId().equals(other.getEmployeeId()))
            && (this.getPassportNumber() == null ? other.getPassportNumber() == null : this.getPassportNumber().equals(other.getPassportNumber()))
            && (this.getPassportExpiry() == null ? other.getPassportExpiry() == null : this.getPassportExpiry().equals(other.getPassportExpiry()))
            && (this.getResidenceStatus() == null ? other.getResidenceStatus() == null : this.getResidenceStatus().equals(other.getResidenceStatus()))
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
        result = prime * result + ((getPassportNumber() == null) ? 0 : getPassportNumber().hashCode());
        result = prime * result + ((getPassportExpiry() == null) ? 0 : getPassportExpiry().hashCode());
        result = prime * result + ((getResidenceStatus() == null) ? 0 : getResidenceStatus().hashCode());
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
        sb.append(", passportNumber=").append(passportNumber);
        sb.append(", passportExpiry=").append(passportExpiry);
        sb.append(", residenceStatus=").append(residenceStatus);
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