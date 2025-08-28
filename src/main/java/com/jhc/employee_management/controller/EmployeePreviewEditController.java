package com.jhc.employee_management.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhc.employee_management.common.ApiResponse;
import com.jhc.employee_management.dto.EmployeeInfoRequest;
import com.jhc.employee_management.dto.LoginRequest;
import com.jhc.employee_management.entity.Employee;
import com.jhc.employee_management.entity.UserLoginInfo;
import com.jhc.employee_management.service.EmployeeService;
import com.jhc.employee_management.service.UserLoginInfoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeePreviewEditController {

    @Resource
    private UserLoginInfoService userLoginInfoService;
    @Resource
    private EmployeeService employeeService;

    @PostMapping("/preview")
    public ResponseEntity<?> employeeInfoGet(@RequestBody LoginRequest request) {
    	
    	
    	//**  ログイン画面の情報より、社員ID取得
        UserLoginInfo userloginInfo = userLoginInfoService.getByUsername(request.getUsername());

        //**  画面に社員の情報を設定
        Employee employeeInfo = employeeService.getById(Long.parseLong(userloginInfo.getEmployeeId()));
        log.info("【employee_preview】社員情報の取得開始，社員ID：{}", userloginInfo.getEmployeeId());
        Map<String, Object> result = new HashMap<>();
        if (employeeInfo == null) {

	        
	        //**  社員ID 
	        result.put("employeeId", userloginInfo.getEmployeeId());
	        result.put("employeeStatus", "0");
            return ResponseEntity.ok(ApiResponse.success("社員情報なし", result));
        }
        else {

	        //**  社員ID 
	        result.put("employeeStatus", "1");
	        //**  社員ID 
	        result.put("employeeId", employeeInfo.getId());
	
	        //**  所属部門名 
	        result.put("DepartmentName", employeeInfo.getDepartmentId());
	
	        //**  従業員名 
	        result.put("name", employeeInfo.getName());
	
	        //**  従業員レベル 
	        result.put("employeeLevel", employeeInfo.getEmployeeLevel());
	
	        //**  メールアドレス 
	        result.put("email", employeeInfo.getEmail());
	
	        //**  電話番号 
	        result.put("phoneNo", employeeInfo.getPhoneNo());
	
	        //**  入社年月日 
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        result.put("hireDate", formatter.format(employeeInfo.getHireDate()));
	
	        //**  役職 
	        result.put("position", employeeInfo.getPosition());
	
	        //**  勤務形態 
	        result.put("employmentType", employeeInfo.getEmploymentType());
	
	        //**  直属上司名 
	        result.put("managerName", employeeInfo.getManagerName());
	
	        //**  緊急連絡先（氏名） 
	        result.put("emergencyContact", employeeInfo.getEmergencyContact());
	
	        //**  緊急連絡先（電話） 
	        result.put("emergencyTel", employeeInfo.getEmergencyTel());
	
	        //**  Slack ID 
	        result.put("slackId", employeeInfo.getSlackId());
	
	        //**  Teams ID 
	        result.put("teamsId", employeeInfo.getTeamsId());
	
	        //**  写真ファイルパス 
	        result.put("photoPath", employeeInfo.getPhotoPath());
	
	        //**  自己PR
	        result.put("selfPr", employeeInfo.getSelfPr());
	        
	        log.info("【employee_preview】社員情報の取得終了，社員ID：{}", userloginInfo.getEmployeeId());
	        return ResponseEntity.ok(ApiResponse.success("社員情報の取得完了", result));

        }
    }

    @PostMapping("/edit")
    public ResponseEntity<?> employeeInfoEdit(@RequestBody EmployeeInfoRequest employeeInfoRequest) throws ParseException {
    	

        //**  画面に社員の情報を取得
        Employee employeeInfo = new Employee();
        log.info("【employee_edit】社員情報の登録開始，社員ID：{}", employeeInfoRequest.getId());
        Map<String, Object> result = new HashMap<>();

        //**  社員ID 
		employeeInfo.setId(employeeInfoRequest.getId());

        //**  所属部門名 
		employeeInfo.setDepartmentId(Long.parseLong("1"));

        //**  従業員名 
		employeeInfo.setName(employeeInfoRequest.getName());

        //**  従業員レベル 
		employeeInfo.setEmployeeLevel(employeeInfoRequest.getEmployeeLevel());

        //**  メールアドレス 
		employeeInfo.setEmail(employeeInfoRequest.getEmail());

        //**  電話番号 
		employeeInfo.setPhoneNo(employeeInfoRequest.getPhoneNo());
		
        //**  入社年月日 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		employeeInfo.setHireDate(formatter.parse(employeeInfoRequest.getHireDate()));

        //**  役職 
		employeeInfo.setPosition(employeeInfoRequest.getPosition());

        //**  勤務形態 
		employeeInfo.setEmploymentType(employeeInfoRequest.getEmploymentType());

        //**  直属上司名 
		employeeInfo.setManagerName(employeeInfoRequest.getManagerName());

        //**  緊急連絡先（氏名） 
		employeeInfo.setEmergencyContact(employeeInfoRequest.getEmergencyContact());

        //**  緊急連絡先（電話） 
		employeeInfo.setEmergencyTel(employeeInfoRequest.getEmergencyTel());

        //**  Slack ID 
		employeeInfo.setSlackId(employeeInfoRequest.getSlackId());

        //**  Teams ID 
		employeeInfo.setTeamsId(employeeInfoRequest.getTeamsId());

        //**  写真ファイルパス 
		employeeInfo.setPhotoPath(employeeInfoRequest.getPhotoPath());

        //**  自己PR
		employeeInfo.setSelfPr(employeeInfoRequest.getSelfPr());

		Date now = new Date();
		employeeInfo.setCreatedAt(now);
		employeeInfo.setUpdatedAt(now);

        //**  更新または新規
		if (employeeInfoRequest.getEmployeeStatus().equals("1") ) {

			employeeService.updateById(employeeInfo);
	        log.info("【employee_edit】社員情報の更新終了，社員ID：{}", employeeInfoRequest.getId());
		}else {

			employeeService.save(employeeInfo);
	        log.info("【employee_edit】社員情報の登録終了，社員ID：{}", employeeInfoRequest.getId());
		}
		
        return ResponseEntity.ok(ApiResponse.success("社員情報の取得完了", result));

 
        
    }
    public Employee getEmployee(Long id) {
        // 直接调用继承自 IService 的方法
        return employeeService.getById(id);
    }
}
