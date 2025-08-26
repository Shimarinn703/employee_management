package com.jhc.employee_management.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

	/**
	 * 技術資格テーブル
	 * @TableName Category
	 */
	@TableName(value ="Category")
	@Data
	public class Category implements Serializable {

	    /**
	     * Category ID (Primary Key)
	     */
	    @TableId("id")
	    private Long id;
	    
	    /**
	     * Category Name
	     */
	    @TableField("category_name")
	    private String categoryName;

	    /**
	     * Category description
	     */
	    @TableField("description")
	    private Long description;

	    /**
	     * Creation Timestamp
	     */
	    @TableField("created_at")
	    private Date createdAt;

	    /**
	     * Update Timestamp
	     */
	    @TableField("updated_at")
	    private Date updatedAt;
	    
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
	        Category other = (Category) that;
	        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
	            && (this.getCategoryName() == null ? other.getCategoryName() == null : this.getCategoryName().equals(other.getCategoryName()))
	            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
	    }

	    @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
	        result = prime * result + ((getCategoryName() == null) ? 0 : getCategoryName().hashCode());
	        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
	        return result;
	    }

	    @Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        sb.append(getClass().getSimpleName());
	        sb.append(" [");
	        sb.append("Hash = ").append(hashCode());
	        sb.append(", id=").append(id);
	        sb.append(", categoryName=").append(categoryName);
	        sb.append(", description=").append(description);
	        sb.append(", serialVersionUID=").append(serialVersionUID);
	        sb.append("]");
	        return sb.toString();
	    }
	}