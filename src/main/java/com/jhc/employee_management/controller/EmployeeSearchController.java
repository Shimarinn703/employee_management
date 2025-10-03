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
     * 社員検索API（分页）
     * @param request 検索条件
     * @return 検索結果（含分页信息）
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
            
            // 分页参数（默认值：第1页，每页10条）
            int page = request.getPage() != null ? request.getPage() : 1;
            int pageSize = request.getPageSize() != null ? request.getPageSize() : 10;
            int offset = (page - 1) * pageSize;
            
            params.put("offset", offset);
            params.put("pageSize", pageSize);
            
            // 获取总记录数
            long total = employeeService.countEmployees(params);
            
            // 检索实行
            List<Map<String, Object>> result = employeeService.searchEmployees(params);
            
            // 检索结果整形
            List<Map<String, Object>> formattedResult = formatSearchResult(result);
            
            // 构建分页响应
            Map<String, Object> pageData = new HashMap<>();
            pageData.put("list", formattedResult);
            pageData.put("total", total);
            pageData.put("page", page);
            pageData.put("pageSize", pageSize);
            pageData.put("totalPages", (int) Math.ceil((double) total / pageSize));
            
            return ApiResponse.success("検索成功", pageData);
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
        // 整形後のマップを作成
        Map<String, Object> formatted = new java.util.LinkedHashMap<>();
        
        // 基本情報
        formatted.put("氏名", detail.get("name"));
        formatted.put("社員ID", detail.get("id"));
        
        // 連絡先情報
        formatted.put("メールアドレス", detail.get("email"));
        formatted.put("電話番号", detail.get("phone_no"));
        
        // 個人情報
        formatted.put("生年月日", detail.get("birthday"));
        formatted.put("性別", detail.get("gender"));
        formatted.put("国籍", detail.get("nationality"));
        formatted.put("婚姻状況", detail.get("marital_status"));
        formatted.put("住所", detail.get("address"));
        formatted.put("最終学歴", detail.get("education"));
        
        // 所属情報
        formatted.put("支店", detail.get("branch_name"));
        formatted.put("部署", detail.get("department_name"));
        formatted.put("役職", detail.get("position"));
        formatted.put("社員レベル", detail.get("employee_level"));
        
        // 勤務情報
        formatted.put("入社年月日", detail.get("hire_date"));
        formatted.put("勤務形態", detail.get("employment_type"));
        formatted.put("直属上司", detail.get("manager_name"));
        
        // 緊急連絡先
        formatted.put("緊急連絡先（氏名）", detail.get("emergency_contact"));
        formatted.put("緊急連絡先（電話）", detail.get("emergency_tel"));
        
        // ツール情報
        formatted.put("Slack ID", detail.get("slack_id"));
        formatted.put("Teams ID", detail.get("teams_id"));
        
        // その他
        formatted.put("自己PR", detail.get("self_pr"));
        formatted.put("写真パス", detail.get("photo_path"));
        
        // nullの値を除外
        formatted.values().removeIf(value -> value == null || value.toString().trim().isEmpty());
        
        return formatted;
    }
}