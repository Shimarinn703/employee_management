package com.jhc.employee_management.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 社員テーブル
 * @TableName employee
 */
@TableName(value ="employee")
@Data
public class Employee implements Serializable {
    /**
     * 社員ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 所属部門ID
     */
    @TableField("department_id")
    private Long departmentId;

    /**
     * 従業員名
     */
    private String name;

    /**
     * 従業員レベル
     */
    @TableField("employee_level")
    private String employeeLevel;

    /**
     * メールアドレス
     */
    private String email;

    /**
     * 電話番号
     */
    @TableField("phone_no")
    private String phoneNo;

    /**
     * 入社年月日
     */
    @TableField("hire_date")
    private Date hireDate;

    /**
     * 役職
     */
    private String position;

    /**
     * 勤務形態
     */
    @TableField("employment_type")
    private String employmentType;

    /**
     * 直属上司名
     */
    @TableField("manager_name")
    private String managerName;

    /**
     * 緊急連絡先（氏名）
     */
    @TableField("emergency_contact")
    private String emergencyContact;

    /**
     * 緊急連絡先（電話）
     */
    @TableField("emergency_tel")
    private String emergencyTel;

    /**
     * Slack ID
     */
    @TableField("slack_id")
    private String slackId;

    /**
     * Teams ID
     */
    @TableField("teams_id")
    private String teamsId;

    /**
     * 写真ファイルパス
     */
    @TableField("photo_path")
    private String photoPath;

    /**
     * 自己PR
     */
    @TableField("self_pr")
    private String selfPr;
    
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
        Employee other = (Employee) that;
        // 4. 使用主键 ID 来判断两个对象是否逻辑相等
        // 注意：这里使用 Objects.equals() 以安全地处理 null 值
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        // 使用主键 ID 的哈希值，与 equals 方法保持一致
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", name='").append(name);
        sb.append(", employeeLevel='").append(employeeLevel);
        sb.append(", email='").append(email);
        sb.append(", phoneNo='").append(phoneNo);
        sb.append(", hireDate=").append(hireDate);
        sb.append(", position='").append(position);
        sb.append(", employmentType='").append(employmentType);
        sb.append(", managerName='").append(managerName);
        sb.append(", emergencyContact='").append(emergencyContact);
        sb.append(", emergencyTel='").append(emergencyTel);
        sb.append(", slackId='").append(slackId);
        sb.append(", teamsId='").append(teamsId);
        sb.append(", photoPath='").append(photoPath);
        sb.append(", selfPr='").append(selfPr);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}