package com.jhc.employee_management.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
import com.jhc.employee_management.dto.StaffSkillRequest;
import com.jhc.employee_management.entity.Employee;
import com.jhc.employee_management.entity.StaffSkill;
import com.jhc.employee_management.entity.Staffbasicinfo;
import com.jhc.employee_management.entity.UserLoginInfo;
import com.jhc.employee_management.service.EmployeeService;
import com.jhc.employee_management.service.StaffSkillService;
import com.jhc.employee_management.service.StaffbasicinfoService;
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
    
    @Resource
    private StaffbasicinfoService basicinfoService;
    
    @Resource
    private StaffSkillService staffSkillService;

    @PostMapping("/preview")
    public ResponseEntity<?> employeeInfoGet(@RequestBody LoginRequest request) {
    	

        log.info("【employee_preview】社員情報の取得開始，ユーザー名：{}", request.getUsername());
    	//**  ログイン画面の情報より、社員ID取得
        UserLoginInfo userloginInfo = userLoginInfoService.getByUsername(request.getUsername());

//**  Employeeから情報は画面に社員の情報を設定
        Employee employeeInfo = employeeService.getById(Long.parseLong(userloginInfo.getEmployeeId()));
        Map<String, Object> result = new HashMap<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (employeeInfo == null) {

	        
	        //**  社員ID 
	        result.put("employeeId", userloginInfo.getEmployeeId());
	        result.put("employeeStatus", "0");
        }
        else {

	        //**  社員状態 
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
	        
        }

        //**  Staffbasicinfoから情報は画面に社員の情報を設定
        Staffbasicinfo staffBasicInfo = basicinfoService.getbyEmployeeId(Long.parseLong(userloginInfo.getEmployeeId()));
        if (staffBasicInfo == null ) {

	        //**  社員基本情報状態
	        result.put("staffBasicInfoStaus", "0");
        }else {

	        //**  社員基本情報状態
	        result.put("staffBasicInfoStaus", "1");
	        
	        //**  社員基本情報ID
	        result.put("staffbasicinfoId", staffBasicInfo.getId());

	        //**  生年月日
	        result.put("birthday", formatter.format(staffBasicInfo.getBirthday()));

	        //**  性別
	        result.put("gender", staffBasicInfo.getGender());

	        //**  住所
	        result.put("address", staffBasicInfo.getAddress());
	        
	        //**  卒業学校
	        result.put("education", staffBasicInfo.getEducation());
        }
        


        //**  StaffSkillから情報は画面に社員の情報を設定
        List<StaffSkill> staffSkillList = staffSkillService.getbyEmployeeId(Long.parseLong(userloginInfo.getEmployeeId()));
        
        List<StaffSkillRequest> staffSkillRequestList = new ArrayList<StaffSkillRequest>();
        for (StaffSkill skill : staffSkillList) {
        	StaffSkillRequest staffSkillRequest = new StaffSkillRequest();
        	
        	//**  スキルID
        	staffSkillRequest.setSkillId(skill.getSkillId());
        	
        	//**  スキル名
        	staffSkillRequest.setName(skill.getName());
        	
        	//**  スキルレベル
        	staffSkillRequest.setLevel(skill.getExperienceLevel());
        	
        	//**  追加
        	staffSkillRequestList.add(staffSkillRequest);
        }

        //**  スキル情報
        result.put("staffSkillRequestList", staffSkillRequestList);
   
        
        log.info("【employee_preview】社員情報の取得終了，ユーザー名：{}", request.getUsername());
        return ResponseEntity.ok(ApiResponse.success("社員情報の取得完了", result));
    }

    @PostMapping("/edit")
    public ResponseEntity<?> employeeInfoEdit(@RequestBody EmployeeInfoRequest employeeInfoRequest) throws ParseException {
    	

        //**  画面に社員の情報を取得
        Employee employeeInfo = new Employee();
		Date now = new Date();
        log.info("【employee_edit】社員情報の登録開始，社員ID：{}", employeeInfoRequest.getId());
        Map<String, Object> result = new HashMap<>();
//**  社員の情報かかわる項目の設定
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
		
        //**  作成＆更新時間 
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

//**  社員基本情報かかわる項目の設定
		Staffbasicinfo staffbasicinfo = new Staffbasicinfo();

        //**  社員ID 
		staffbasicinfo.setEmployeeId(employeeInfoRequest.getId());
		
        //**  生年月日 
		staffbasicinfo.setBirthday(formatter.parse(employeeInfoRequest.getBirthday()));
		
        //**  性別 
		staffbasicinfo.setGender(employeeInfoRequest.getGender());
		
        //**  住所 
		staffbasicinfo.setAddress(employeeInfoRequest.getAddress());
        //**  住所 
		staffbasicinfo.setEducation(employeeInfoRequest.getEducation());
		
        //**  作成＆更新時間 
		staffbasicinfo.setCreatedAt(now);
		staffbasicinfo.setUpdatedAt(now);

        //**  更新または新規
		if (employeeInfoRequest.getStaffBasicInfoStaus().equals("1") ) {

			basicinfoService.updateByEmployeeId(staffbasicinfo);
	        log.info("【employee_edit】社員基本情報の更新終了，社員ID：{}", employeeInfoRequest.getId());
		}else {

			basicinfoService.save(staffbasicinfo);
	        log.info("【employee_edit】社員基本情報の登録終了，社員ID：{}", employeeInfoRequest.getId());
		}
		
		

//**  社員スキル情報かかわる項目の設定
		staffSkillService.removeByEmployeeId(employeeInfoRequest.getId());
		Collection<StaffSkill> staffSkilCollection = new HashSet<>();
		for (StaffSkillRequest staffSkillrequest : employeeInfoRequest.getStaffSkillRequestList()) {
			StaffSkill staffSkill = new StaffSkill();

	        //**  社員ID 
			staffSkill.setEmployeeId(employeeInfoRequest.getId());

	        //**  スキルID 
			staffSkill.setSkillId(staffSkillrequest.getSkillId());

	        //**  スキルレベル 
			staffSkill.setExperienceLevel(staffSkillrequest.getLevel());
			
	        //**  作成＆更新時間 
			staffSkill.setUpdatedAt(now);
			staffSkill.setCreatedAt(now);
			staffSkilCollection.add(staffSkill);
		}
		
		//**  全件データ社員スキル情報に登録
		staffSkillService.saveBatch(staffSkilCollection);
        log.info("【employee_edit】社員スキル情報の登録終了，社員ID：{}", employeeInfoRequest.getId());
		
        return ResponseEntity.ok(ApiResponse.success("社員情報の保存完了", result));

    }

}
