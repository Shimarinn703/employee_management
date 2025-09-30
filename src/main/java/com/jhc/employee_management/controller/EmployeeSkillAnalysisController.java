package com.jhc.employee_management.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhc.employee_management.common.ApiResponse;
import com.jhc.employee_management.entity.Employee;
import com.jhc.employee_management.entity.StaffSkill;
import com.jhc.employee_management.service.EmployeeService;
import com.jhc.employee_management.service.StaffSkillService;

/**
 * 社員スキル分析APIコントローラ
 */
@RestController
@RequestMapping("/employee/skill-analysis")
public class EmployeeSkillAnalysisController {

    @Resource
    private EmployeeService employeeService;
    
    @Resource
    private StaffSkillService staffSkillService;

    /**
     * 個別社員スキル分析API
     * @param id 社員ID
     * @return 社員スキル分析データ
     */
    @GetMapping("/by-employee/{id}")
    public ApiResponse getEmployeeSkillAnalysis(@PathVariable Long id) {
        try {
            // 社員基本情報取得
            Employee employee = employeeService.getbyEmployeeId(id);
            if (employee == null) {
                return ApiResponse.error(404, "社員情報が見つかりません");
            }
            
            // 社員スキル情報取得
            List<StaffSkill> skills = staffSkillService.getbyEmployeeId(id);
            
            // スキルカテゴリ別に集計
            Map<String, Double> categoryAverage = calculateCategoryAverage(skills);
            
            // データ整形
            Map<String, Object> result = new HashMap<>();
            result.put("employee", formatEmployeeInfo(employee));
            result.put("skills", formatSkills(skills));
            result.put("categoryAverage", categoryAverage);
            
            return ApiResponse.success("スキル分析成功", result);
        } catch (Exception e) {
            return ApiResponse.error(500, "スキル分析エラー: " + e.getMessage());
        }
    }

    /**
     * 複数社員スキル比較API
     * @param request リクエストボディ（employeeIdsを含む）
     * @return 複数社員スキル比較データ
     */
    @PostMapping("/by-employees")
    public ApiResponse compareEmployeesSkills(@RequestBody Map<String, Object> request) {
        try {
            // リクエストから社員IDリストを取得し、Integer型からLong型に変換
            List<Integer> intEmployeeIds = (List<Integer>) request.get("employeeIds");
            if (intEmployeeIds == null || intEmployeeIds.isEmpty()) {
                return ApiResponse.error(400, "社員IDリストが空です");
            }
            
            // Integer型のリストをLong型のリストに変換
            List<Long> employeeIds = intEmployeeIds.stream()
                .map(Integer::longValue)
                .collect(Collectors.toList());
            
            // 最大3名までの社員比較を許可
            if (employeeIds.size() > 3) {
                employeeIds = employeeIds.subList(0, 3);
            }
            
            // 各社員のスキル情報を取得
            List<Map<String, Object>> employeesData = new ArrayList<>();
            for (Long id : employeeIds) {
                Employee employee = employeeService.getbyEmployeeId(id);
                if (employee != null) {
                    List<StaffSkill> skills = staffSkillService.getbyEmployeeId(id);
                    
                    Map<String, Object> employeeData = new HashMap<>();
                    employeeData.put("id", employee.getId());
                    employeeData.put("name", employee.getName());
                    employeeData.put("skills", formatSkills(skills));
                    
                    employeesData.add(employeeData);
                }
            }
            
            return ApiResponse.success("スキル比較成功", employeesData);
        } catch (Exception e) {
            return ApiResponse.error(500, "スキル比較エラー: " + e.getMessage());
        }
    }

    /**
     * 部署別スキル分析API
     * @param departmentId 部署ID
     * @return 部署スキル分析データ
     */
    @GetMapping("/by-department/{departmentId}")
    public ApiResponse getDepartmentSkillAnalysis(@PathVariable String departmentId) {
        try {
            // 部署の全社員取得
            Map<String, Object> params = new HashMap<>();
            params.put("department", departmentId);
            List<Map<String, Object>> employees = employeeService.searchEmployees(params);
            
            // 全社員のスキル情報を取得し集計
            Map<String, Double> skillSummary = new HashMap<>();
            Map<String, Integer> skillCount = new HashMap<>();
            
            for (Map<String, Object> emp : employees) {
                Long employeeId = ((Number) emp.get("id")).longValue();
                List<StaffSkill> skills = staffSkillService.getbyEmployeeId(employeeId);
                
                for (StaffSkill skill : skills) {
                    String skillName = skill.getName();
                    int level = skill.getExperienceLevel();
                    
                    skillSummary.put(skillName, skillSummary.getOrDefault(skillName, 0.0) + level);
                    skillCount.put(skillName, skillCount.getOrDefault(skillName, 0) + 1);
                }
            }
            
            // 平均スキルレベルを計算
            Map<String, Double> averageSkills = new HashMap<>();
            for (Map.Entry<String, Double> entry : skillSummary.entrySet()) {
                String skillName = entry.getKey();
                double totalLevel = entry.getValue();
                int count = skillCount.get(skillName);
                averageSkills.put(skillName, totalLevel / count);
            }
            
            // スキルカテゴリ別に集計
            List<StaffSkill> allSkills = new ArrayList<>();
            for (Map<String, Object> emp : employees) {
                Long employeeId = ((Number) emp.get("id")).longValue();
                allSkills.addAll(staffSkillService.getbyEmployeeId(employeeId));
            }
            
            Map<String, Double> categoryAverage = calculateCategoryAverage(allSkills);
            
            Map<String, Object> result = new HashMap<>();
            result.put("departmentId", departmentId);
            result.put("employeeCount", employees.size());
            result.put("averageSkills", averageSkills);
            result.put("categoryAverage", categoryAverage);
            
            return ApiResponse.success("部署スキル分析成功", result);
        } catch (Exception e) {
            return ApiResponse.error(500, "部署スキル分析エラー: " + e.getMessage());
        }
    }
    
    /**
     * スキルカテゴリ別の平均レベルを計算
     */
    private Map<String, Double> calculateCategoryAverage(List<StaffSkill> skills) {
        Map<String, Double> categoryTotal = new HashMap<>();
        Map<String, Integer> categoryCount = new HashMap<>();
        
        for (StaffSkill skill : skills) {
            // スキル名からカテゴリを推測
            String category = getSkillCategory(skill.getName());
            
            categoryTotal.put(category, categoryTotal.getOrDefault(category, 0.0) + skill.getExperienceLevel());
            categoryCount.put(category, categoryCount.getOrDefault(category, 0) + 1);
        }
        
        // 平均を計算
        Map<String, Double> categoryAverage = new HashMap<>();
        for (Map.Entry<String, Double> entry : categoryTotal.entrySet()) {
            String category = entry.getKey();
            double totalLevel = entry.getValue();
            int count = categoryCount.get(category);
            categoryAverage.put(category, totalLevel / count);
        }
        
        return categoryAverage;
    }
    
    /**
     * スキル名からカテゴリを推測
     */
    private String getSkillCategory(String skillName) {
        // 単純なカテゴリ分けロジック
        if (skillName.equals("Java") || skillName.equals("Python") || skillName.equals("JavaScript") || 
            skillName.equals("TypeScript") || skillName.equals("Node.js")) {
            return "プログラミング言語";
        } else if (skillName.equals("AWS") || skillName.equals("Azure") || skillName.equals("GCP") || 
                   skillName.equals("OpenStack") || skillName.equals("Serverless (Lambda等)")) {
            return "クラウドサービス";
        } else if (skillName.equals("React") || skillName.equals("Vue.js") || skillName.equals("Angular") || 
                   skillName.equals("Svelte")) {
            return "フロントエンドフレームワーク";
        } else if (skillName.equals("Spring Boot") || skillName.equals("Django") || skillName.equals("Ruby on Rails")) {
            return "バックエンドフレームワーク";
        } else if (skillName.equals("MySQL") || skillName.equals("PostgreSQL")) {
            return "データベース";
        } else if (skillName.equals("Flutter") || skillName.equals("React Native")) {
            return "モバイル開発";
        } else if (skillName.equals("Kafka") || skillName.equals("Elasticsearch")) {
            return "データエンジニアリング";
        } else {
            return "その他";
        }
    }
    
    /**
     * 社員情報を整形
     */
    private Map<String, Object> formatEmployeeInfo(Employee employee) {
        Map<String, Object> formatted = new HashMap<>();
        formatted.put("id", employee.getId());
        formatted.put("name", employee.getName());
        formatted.put("department", employee.getDepartmentId());
        formatted.put("position", employee.getPosition());
        // その他必要なフィールドを追加
        return formatted;
    }
    
    /**
     * スキル情報を整形
     */
    private List<Map<String, Object>> formatSkills(List<StaffSkill> skills) {
        return skills.stream().map(skill -> {
            Map<String, Object> formatted = new HashMap<>();
            formatted.put("skillId", skill.getSkillId());
            formatted.put("name", skill.getName());
            formatted.put("level", skill.getExperienceLevel());
            return formatted;
        }).collect(Collectors.toList());
    }
}