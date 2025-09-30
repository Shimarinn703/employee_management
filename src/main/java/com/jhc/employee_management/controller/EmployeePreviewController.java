package com.jhc.employee_management.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhc.employee_management.common.ApiResponse;

/**
 * 社員情報プレビュー専用コントローラ
 */
// @RestController
// @RequestMapping("/employee")
public class EmployeePreviewController {

    /**
     * 社員情報プレビューAPI
     * @param request リクエストパラメータ
     * @return 社員プレビュー情報
     */
    @PostMapping("/preview")
    public ApiResponse previewEmployee(@RequestBody Map<String, Object> request) {
        try {
            // シンプルなモックデータを返す
            Map<String, Object> employeeData = new HashMap<>();
            employeeData.put("employeeId", "0000000001");
            employeeData.put("name", "山田 太郎");
            employeeData.put("departmentName", "開発部");
            employeeData.put("position", "シニアエンジニア");
            employeeData.put("email", "yamada.taro@example.com");
            
            return ApiResponse.success("プレビュー成功", employeeData);
        } catch (Exception e) {
            return ApiResponse.error(500, "プレビューエラー: " + e.getMessage());
        }
    }
}