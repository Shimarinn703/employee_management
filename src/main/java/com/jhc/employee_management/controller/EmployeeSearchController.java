package com.jhc.employee_management.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhc.employee_management.common.ApiResponse;
import com.jhc.employee_management.dto.EmployeeSearchRequest;
import com.jhc.employee_management.service.EmployeeService;

/**
 * 社員検索APIコントローラ
 */
@RestController
@RequestMapping("/employee")
public class EmployeeSearchController {

    @Resource
    private EmployeeService employeeService;

    /**
     * 社員検索API
     * @param request 検索条件
     * @return 検索結果
     */
    @PostMapping("/search")
    public ApiResponse searchEmployees(@RequestBody EmployeeSearchRequest request) {
        try {
            // 検索条件をMapに変換
            Map<String, Object> params = new HashMap<>();
            params.put("gender", request.getGender());
            params.put("minAge", request.getMinAge());
            params.put("maxAge", request.getMaxAge());
            params.put("minExperience", request.getMinExperience());
            params.put("maxExperience", request.getMaxExperience());
            params.put("skills", request.getSkills());
            params.put("techLevel", request.getTechLevel());
            params.put("techExpYears", request.getTechExpYears());
            params.put("category", request.getCategory());
            params.put("department", request.getDepartment());
            
            // 検索実行
            List<Map<String, Object>> result = employeeService.searchEmployees(params);
            
            // 検索結果の整形
            List<Map<String, Object>> formattedResult = formatSearchResult(result);
            
            return ApiResponse.success("検索成功", formattedResult);
        } catch (Exception e) {
            return ApiResponse.error(500, "検索エラー: " + e.getMessage());
        }
    }
    

    /**
     * 社員詳細情報取得API
     * @param id 社員ID
     * @return 社員詳細情報
     */
    @GetMapping("/detail/{id}")
    public ApiResponse getEmployeeDetail(@PathVariable Long id) {
        try {
            Map<String, Object> detail = employeeService.getEmployeeDetail(id);
            if (detail == null) {
                return ApiResponse.error(404, "社員情報が見つかりません");
            }
            
            // 詳細情報の整形
            Map<String, Object> formattedDetail = formatDetailResult(detail);
            
            return ApiResponse.success("詳細取得成功", formattedDetail);
        } catch (Exception e) {
            return ApiResponse.error(500, "詳細情報取得エラー: " + e.getMessage());
        }
    }

    /**
     * 検索結果を整形する
     * @param result 検索結果
     * @return 整形後の検索結果
     */
    private List<Map<String, Object>> formatSearchResult(List<Map<String, Object>> result) {
        // ここで検索結果をフロントエンドの要求に合わせて整形する
        // 現在の実装では、そのまま返していますが、必要に応じてカスタマイズできます
        return result;
    }

    /**
     * 詳細情報を整形する
     * @param detail 詳細情報
     * @return 整形後の詳細情報
     */
    private Map<String, Object> formatDetailResult(Map<String, Object> detail) {
        // ここで詳細情報をフロントエンドの要求に合わせて整形する
        // 現在の実装では、そのまま返していますが、必要に応じてカスタマイズできます
        return detail;
    }
}