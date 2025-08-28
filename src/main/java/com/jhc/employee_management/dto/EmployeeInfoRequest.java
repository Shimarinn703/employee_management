package com.jhc.employee_management.dto;

import java.util.Date;
import java.util.Map;

import lombok.Data;

@Data
public class EmployeeInfoRequest {
	

/** Table Employee 向け     */


	/**
     * 社員tableに状態
     * 0：なし
     * 1：存在
     */
    private String employeeStatus ;

	/**
     * 社員ID
     */
    private Long id;
    
    /**
     * 所属部門名
     */
    private String departmentName;

    /**
     * 従業員名
     */
    private String name;

    /**
     * 従業員レベル
     */
    private String employeeLevel;

    /**
     * メールアドレス
     */
    private String email;

    /**
     * 電話番号
     */
    private String phoneNo;

    /**
     * 入社年月日
     */
    private String hireDate;

    /**
     * 役職
     */
    private String position;

    /**
     * 勤務形態
     */
    private String employmentType;

    /**
     * 直属上司名
     */
    private String managerName;

    /**
     * 緊急連絡先（氏名）
     */
    private String emergencyContact;

    /**
     * 緊急連絡先（電話）
     */
    private String emergencyTel;

    /**
     * Slack ID
     */
    private String slackId;

    /**
     * Teams ID
     */
    private String teamsId;

    /**
     * 写真ファイルパス
     */
    private String photoPath;

    /**
     * 自己PR
     */
    private String selfPr;

/** Table staffbasicinfo 向け     */
    
    /**
     * 生年月日
     */
    private String birthday;

    /**
     * 性別
     */
    private String gender;
    
    /**
     * 住所
     */
    private String address;

/** Table staffEducation  向け     */
    /**
     * 入学年月日
     */
    private Date startYmd;
    
    /**
     * 卒業年月日
     */
    private Date endYmd;
    
    /**
     * 卒業学校
     */
    private String schoolName;
    
    /**
    * 学部名称
    */
   private String facultyName;

   /**
    * 学科名称
    */
   private String department;

    
/** Table staffskill 向け         */
    
    /**
     * 社員スキルアップ
     */
    Map <Integer, String> staffSkilMap;

/** Table staffCategory 向け     */
    
    /**
     * 社員技術カテゴリマスタアップ
     */
    Map <Long, String> staffCategoryMap;
    
/** Table staffProject 向け     */
    
    /**
     * 社員プロジェクトリストアップ
     */
    Map <Long, String> staffProjectMap;

}
