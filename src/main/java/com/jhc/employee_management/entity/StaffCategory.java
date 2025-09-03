

package com.jhc.employee_management.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

	/**
	 * 社員技術資格テーブル
	 * @TableName staffcategory
	 */
	@TableName(value ="staffcategory")
	@Data
	public class StaffCategory implements Serializable {
	    /**
	     * ID
	     */
	    @TableId(value = "id", type = IdType.AUTO)
	    private Long id;

	    /**
	     * 社員ID
	     */
	    @TableField(value = "employee_id")
	    private Long employeeId;

	    /**
	     * 技術資格ID
	     */
	    @TableField(value = "category_id")
	    private String categoryId;
	    
	    /**
	     * 技術資格名
	     */
	    @TableField("category_name")
	    private String categoryName;
	    
	    /**
	     * 取得年月日
	     */
	    @TableField(value = "get_ymd")
	    private String getYmd;

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
	        StaffCategory other = (StaffCategory) that;
	        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
	            && (this.getEmployeeId() == null ? other.getEmployeeId() == null : this.getEmployeeId().equals(other.getEmployeeId()))
	            && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
	            && (this.getGetYmd() == null ? other.getGetYmd() == null : this.getGetYmd().equals(other.getGetYmd()));
	    }

	    @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
	        result = prime * result + ((getEmployeeId() == null) ? 0 : getEmployeeId().hashCode());
	        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
	        result = prime * result + ((getGetYmd() == null) ? 0 : getGetYmd().hashCode());
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
	        sb.append(", categoryId=").append(categoryId);
	        sb.append(", getYmd=").append(getYmd);
	        sb.append(", serialVersionUID=").append(serialVersionUID);
	        sb.append("]");
	        return sb.toString();
	    }
	}
