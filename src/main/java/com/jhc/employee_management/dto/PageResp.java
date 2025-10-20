package com.jhc.employee_management.dto;
import java.util.List;

// 日本語コメント：ページングレスポンス（content/totalElements のみ）
public class PageResp<T> {
    private List<T> content;
    private long totalElements;

    public PageResp() {}

    public PageResp(List<T> content, long totalElements) {
        this.content = content;
        this.totalElements = totalElements;
    }

    public List<T> getContent() { return content; }
    public void setContent(List<T> content) { this.content = content; }
    public long getTotalElements() { return totalElements; }
    public void setTotalElements(long totalElements) { this.totalElements = totalElements; }
}
