package com.jhc.employee_management.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Integer role;
}