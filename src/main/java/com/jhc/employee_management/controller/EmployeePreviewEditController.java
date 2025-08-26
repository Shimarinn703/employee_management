package com.jhc.employee_management.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhc.employee_management.common.ApiResponse;
import com.jhc.employee_management.entity.Employee;
import com.jhc.employee_management.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeePreviewEditController {

    @Resource
    private EmployeeService employeeService;

    @PostMapping("/{id}")
    public ResponseEntity<?> employeeInfoGet(@PathVariable Long id) {
    	
    	
        log.info("【preview】社員情報の取得開始，社員ID：{}", id);

    	
        /**
         * 画面に社員の情報を設定
         */
        Employee employeeInfo = employeeService.getById(id);
        Map<String, Object> result = new HashMap<>();
        if (employeeInfo == null) {

            return ResponseEntity.ok(ApiResponse.success("社員情報なし", null));
        }
        else {
	        
	        /**
	         * 社員ID
	         */
	        result.put("employeeId", employeeInfo.getId());
	
	        /**
	         * 所属部門名
	         */
	        result.put("DepartmentName", employeeInfo.getDepartmentId());
	
	        /**
	         * 従業員名
	         */
	        result.put("name", employeeInfo.getName());
	
	        /**
	         * 従業員レベル
	         */
	        result.put("employeeLevel", employeeInfo.getEmployeeLevel());
	
	        /**
	         * メールアドレス
	         */
	        result.put("email", employeeInfo.getEmail());
	
	        /**
	         * 電話番号
	         */
	        result.put("phoneNo", employeeInfo.getPhoneNo());
	
	        /**
	         * 入社年月日
	         */
	        result.put("hireDate", employeeInfo.getHireDate());
	
	        /**
	         * 役職
	         */
	        result.put("position", employeeInfo.getPosition());
	
	        /**
	         * 勤務形態
	         */
	        result.put("employmentType", employeeInfo.getEmploymentType());
	
	        /**
	         * 直属上司名
	         */
	        result.put("managerName", employeeInfo.getManagerName());
	
	        /**
	         * 緊急連絡先（氏名）
	         */
	        result.put("emergencyContact", employeeInfo.getEmergencyContact());
	
	        /**
	         * 緊急連絡先（電話）
	         */
	        result.put("emergencyTel", employeeInfo.getEmergencyTel());
	
	        /**
	         * Slack ID
	         */
	        result.put("slackId", employeeInfo.getSlackId());
	
	        /**
	         * Teams ID
	         */
	        result.put("teamsId", employeeInfo.getTeamsId());
	
	        /**
	         * 写真ファイルパス
	         */
	        result.put("photoPath", employeeInfo.getPhotoPath());
	
	        /**
	         * 自己PR
	         */
	        result.put("selfPr", employeeInfo.getSelfPr());
	        
	        log.info("【preview】社員情報の取得終了，社員ID：{}", id);
	        return ResponseEntity.ok(ApiResponse.success("社員情報の取得完了", result));

        }
 
        
    }

    public Employee getEmployee(Long id) {
        // 直接调用继承自 IService 的方法
        return employeeService.getById(id);
    }
}
