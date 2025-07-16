package com.jhc.employee_management.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
