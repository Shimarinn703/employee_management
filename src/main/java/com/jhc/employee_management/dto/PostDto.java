package com.jhc.employee_management.dto;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/** 投稿一覧返却用DTO */
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PostDto{
    private Long id;
    private String content;
    private long likeCount;
    private List<String> images;
}