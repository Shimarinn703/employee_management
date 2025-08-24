package com.jhc.employee_management.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class LearningRequest {
    private String title;
    private String link;
    private String username;
    private Long creatorId;
}
