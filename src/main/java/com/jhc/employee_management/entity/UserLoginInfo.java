package com.jhc.employee_management.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;


@Data
@TableName("user_login_info")
public class UserLoginInfo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String employeeId;
    private String username;
    private String password;
    private Date loginTime;
    private Date createdAt;
    private Date updatedAt;
    private String add1;
    private String add2;
    private String add3;
}
