package com.jhc.employee_management.dto;

import java.util.List;

import lombok.Data;

/**
 * 従業員検索リクエスト
 */
@Data
public class EmployeeSearchRequest {
    /**
     * スキルカテゴリ
     */
    private String category;
    
    /**
     * スキル名リスト
     */
    private List<String> skills;
    
    /**
     * 技術レベル
     */
    private String techLevel;
    
    /**
     * 経験年数
     */
    private Integer techExpYears;
    
    /**
     * 性別
     */
    private String gender;
    
    /**
     * 最小年齢
     */
    private Integer minAge;
    
    /**
     * 最大年齢
     */
    private Integer maxAge;
    
    /**
     * 最小仕事年数
     */
    private Integer minExperience;
    
    /**
     * 最大仕事年数
     */
    private Integer maxExperience;
    
    /**
     * 部署
     */
    private String department;
    
    /**
     * ページ番号（デフォルト: 1）
     */
    private Integer page;
    
    /**
     * 1ページあたりの件数（デフォルト: 10）
     */
    private Integer pageSize;
}