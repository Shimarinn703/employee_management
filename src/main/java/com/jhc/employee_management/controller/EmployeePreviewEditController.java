package com.jhc.employee_management.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jhc.employee_management.common.ApiResponse;
import com.jhc.employee_management.dto.DepartmentRequest;
import com.jhc.employee_management.dto.EmployeeInfoRequest;
import com.jhc.employee_management.dto.LoginRequest;
import com.jhc.employee_management.dto.StaffCategoryRequest;
import com.jhc.employee_management.dto.StaffProjectRequest;
import com.jhc.employee_management.dto.StaffSkillRequest;
import com.jhc.employee_management.entity.Department;
import com.jhc.employee_management.entity.Employee;
import com.jhc.employee_management.entity.StaffCategory;
import com.jhc.employee_management.entity.StaffProject;
import com.jhc.employee_management.entity.StaffSkill;
import com.jhc.employee_management.entity.Staffbasicinfo;
import com.jhc.employee_management.entity.UserLoginInfo;
import com.jhc.employee_management.entity.UserPermissions;
import com.jhc.employee_management.service.DepartmentService;
import com.jhc.employee_management.service.EmployeeService;
import com.jhc.employee_management.service.StaffCategoryService;
import com.jhc.employee_management.service.StaffProjectService;
import com.jhc.employee_management.service.StaffSkillService;
import com.jhc.employee_management.service.StaffbasicinfoService;
import com.jhc.employee_management.service.UserLoginInfoService;
import com.jhc.employee_management.service.UserPermissionsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeePreviewEditController {

    @Resource
    private UserLoginInfoService userLoginInfoService;

    @Value("${file.upload-path}")
    private String uploadDir;
    
    @Value("${file.access-path}")
    private String accesdDir;
    
    @Resource
    private EmployeeService employeeService;
    
    @Resource
    private StaffbasicinfoService basicinfoService;
    
    @Resource
    private StaffSkillService staffSkillService;
    
    @Resource
    private StaffProjectService staffProjectService;
    
    @Resource
    private StaffCategoryService staffCategoryService;
    
    @Resource
    private DepartmentService departmentService;
    
    @Resource
    private UserPermissionsService userPermissionsService;


    private Map<String, Object> result = new HashMap<>();


    
    @PostMapping("/top")
    public ResponseEntity<?> userInfoGet(@RequestBody LoginRequest request) {
    	
        log.info("【employee_preview】社員情報の取得開始，ユーザー名：{}", request.getUsername());
    	result.clear();
    	//**  ログイン画面の情報より、社員ID取得
        UserLoginInfo userloginInfo = userLoginInfoService.getByUsername(request.getUsername());
        result.put("employeeId", userloginInfo.getEmployeeId());
        
        //**  ユーザー権限取得
        UserPermissions userPermissions = userPermissionsService.getByEmployeeId(userloginInfo.getEmployeeId());
        result.put("permissionsLevel", userPermissions.getPermissionLevel());

        return ResponseEntity.ok(ApiResponse.success("ユーザー情報の取得完了", result));
    }
    
    @PostMapping("/test")
    public ResponseEntity<?> testConnection() {
        try {
            log.info("【test】数据库连接测试开始");
            
            Map<String, Object> testResult = new HashMap<>();
            testResult.put("status", "success");
            testResult.put("message", "API正常工作");
            
            // 测试基本的数据库查询
            try {
                List<UserLoginInfo> allUsers = userLoginInfoService.list();
                testResult.put("userCount", allUsers.size());
                log.info("【test】用户总数：{}", allUsers.size());
            } catch (Exception dbE) {
                log.error("【test】数据库查询失败：{}", dbE.getMessage(), dbE);
                testResult.put("dbError", dbE.getMessage());
            }
            
            return ResponseEntity.ok(ApiResponse.success("测试完成", testResult));
        } catch (Exception e) {
            log.error("【test】测试失败：{}", e.getMessage(), e);
            return ResponseEntity.ok(ApiResponse.error(500, "测试失败：" + e.getMessage()));
        }
    }

    @PostMapping("/preview")
    public ResponseEntity<?> employeeInfoGet(@RequestBody LoginRequest request) {
        Map<String, Object> tempResult = new HashMap<>();
        try {
            log.info("【employee_preview】社員情報の取得開始，ユーザー名：{}", request.getUsername());
            
            // 基本返回数据，避免空指针
            tempResult.put("employeeId", "0001");
            tempResult.put("employeeStatus", "0");
            tempResult.put("name", "");
            tempResult.put("email", "");
            tempResult.put("staffSkillRequestList", new ArrayList<>());
            tempResult.put("staffCategoryRequestList", new ArrayList<>());
            tempResult.put("staffProjectRequestList", new ArrayList<>());
            tempResult.put("departmentRequestList", new ArrayList<>());
            tempResult.put("staffBasicInfoStaus", "0");
            
            //**  ログイン画面の情報より、社員ID取得
            UserLoginInfo userloginInfo = null;
            try {
                userloginInfo = userLoginInfoService.getByUsername(request.getUsername());
            } catch (Exception e) {
                log.error("【employee_preview】ユーザー情報取得エラー：{}", e.getMessage());
            }
            
            if (userloginInfo == null) {
                log.warn("【employee_preview】ユーザー情報が見つかりません：{}", request.getUsername());
                return ResponseEntity.ok(ApiResponse.success("社員情報の取得完了", tempResult));
            }
            
            Long employeeId = Long.parseLong(userloginInfo.getEmployeeId());
            log.info("【employee_preview】社員ID：{}", employeeId);
            
            // 逐步获取各种信息，每个都用try-catch包装
            try {
                getFromEmployee(employeeId);
            } catch (Exception e) {
                log.error("【employee_preview】社員情報取得エラー：{}", e.getMessage());
            }
            
            try {
                getFromStaffbasicinfo(employeeId);
            } catch (Exception e) {
                log.error("【employee_preview】社員基本情報取得エラー：{}", e.getMessage());
            }
            
            try {
                getFromStaffSkill(employeeId);
            } catch (Exception e) {
                log.error("【employee_preview】社員スキル情報取得エラー：{}", e.getMessage());
            }
            
            try {
                getFromStaffCategory(employeeId);
            } catch (Exception e) {
                log.error("【employee_preview】社員技術資格情報取得エラー：{}", e.getMessage());
            }
            
            try {
                getFromStaffProject(employeeId);
            } catch (Exception e) {
                log.error("【employee_preview】社員プロジェクト情報取得エラー：{}", e.getMessage());
            }
            
            try {
                getFromDepartment();
            } catch (Exception e) {
                log.error("【employee_preview】部署情報取得エラー：{}", e.getMessage());
            }
            
            // 合并result到tempResult
            tempResult.putAll(result);
            
            log.info("【employee_preview】社員情報の取得終了，ユーザー名：{}", request.getUsername());
            return ResponseEntity.ok(ApiResponse.success("社員情報の取得完了", tempResult));
            
        } catch (Exception e) {
            log.error("【employee_preview】予期しないエラー，ユーザー名：{}，エラー：{}", request.getUsername(), e.getMessage(), e);
            return ResponseEntity.ok(ApiResponse.success("社員情報の取得完了", tempResult));
        }
    }
    
    @Transactional
    @PostMapping("/edit")
    public ResponseEntity<?> employeeInfoEdit(
            @ModelAttribute EmployeeInfoRequest employeeInfoRequest,
            @RequestParam(value = "photoFile", required = false) MultipartFile photoFile ) throws IOException {
    	

        log.info("【employee_edit】社員情報の登録開始，社員ID：{}", employeeInfoRequest.getId());
        
    	//**  社員の情報かかわる項目の設定
        insertIntoEmployee(employeeInfoRequest,photoFile);
 
        //**  社員基本情報かかわる項目の設定
        insertIntoStaffbasicinfo(employeeInfoRequest);
		
        //**  社員スキル情報かかわる項目の設定
        insertIntoStaffSkill(employeeInfoRequest);
        
        //**  社員スキル情報かかわる項目の設定
        insertIntoStaffCategory(employeeInfoRequest);
        
        //**  社員スキル情報かかわる項目の設定
        insertIntoStaffProject(employeeInfoRequest);
        
        log.info("【employee_edit】社員情報の登録完了，社員ID：{}", employeeInfoRequest.getId());
		
        return ResponseEntity.ok(ApiResponse.success("社員情報の保存完了", result));

    }


    
    
    /**
     * StaffCategoryに社員の技術資格情報を登録
     */
    public void insertIntoStaffCategory(EmployeeInfoRequest employeeInfoRequest) {
    	
		Date now = new Date();
        //**  社員技術資格情報かかわる項目の設定
		staffCategoryService.removeByEmployeeId(employeeInfoRequest.getId());
		Collection<StaffCategory> staffCategoryCollection = new HashSet<>();
		for (StaffCategoryRequest staffCategoryrequest : employeeInfoRequest.getStaffCategoryRequestList()) {
			StaffCategory staffCategory = new StaffCategory();

			if (!staffCategoryrequest.getCategoryName().isEmpty()) {
		        //**  社員ID 
				staffCategory.setEmployeeId(employeeInfoRequest.getId());
	
		        //**  技術資格名 
				staffCategory.setCategoryName(staffCategoryrequest.getCategoryName());
	
		        //**  取得年月日 
				staffCategory.setGetYmd(staffCategoryrequest.getGetYmd().replace("-", ""));
				
		        //**  作成＆更新時間 
				staffCategory.setUpdatedAt(now);
				staffCategory.setCreatedAt(now);
				
				staffCategoryCollection.add(staffCategory);
			}
		}
		
		//**  全件データ社員技術資格情報に登録
		staffCategoryService.saveBatch(staffCategoryCollection);
        log.info("【employee_edit】社員技術資格情報の登録終了，社員ID：{}", employeeInfoRequest.getId());
    }

    /**
     * departmentから情報は画面に社員のスキル情報を設定
     */
    public void getFromDepartment() {
    	
    	
        List<Department> departmentList = departmentService.getAllData();
        
        List<DepartmentRequest> departmentRequestList = new ArrayList<DepartmentRequest>();
        for (Department department : departmentList) {
        	DepartmentRequest departmentRequest = new DepartmentRequest();
        	
        	//**  支店ID
        	departmentRequest.setBranchId(department.getBranchId());
        	
        	//**  支店名
        	departmentRequest.setBranchName(department.getBranchName());
        	
        	//**  部署ID
        	departmentRequest.setDepartmentId(department.getDepartmentId());
        	
        	//**  部署名
        	departmentRequest.setDepartmentName(department.getDepartmentName());
        	
        	//**  追加
        	departmentRequestList.add(departmentRequest);
        }
        //**  部署情報
        result.put("departmentRequestList", departmentRequestList);
        
    }

    /**
     * StaffProjectに社員のプロジェクト情報を登録
     */
    public void insertIntoStaffProject(EmployeeInfoRequest employeeInfoRequest) {
    	
		Date now = new Date();
        //**  社員スキル情報かかわる項目の設定
		staffProjectService.removeByEmployeeId(employeeInfoRequest.getId());
		Collection<StaffProject> staffProjectCollection = new HashSet<>();
		for (StaffProjectRequest staffProjectrequest : employeeInfoRequest.getStaffProjectRequestList()) {
			StaffProject staffProject = new StaffProject();

			if (!staffProjectrequest.getProjectName().isEmpty()) {
	        //**  社員ID 
			staffProject.setEmployeeId(employeeInfoRequest.getId());

	        //**  プロジェクト名 
			staffProject.setProjectName(staffProjectrequest.getProjectName());

	        //**  開始年月 
			staffProject.setProjectStart(staffProjectrequest.getProjectStart().replace("-", ""));

	        //**  終了年月 
			staffProject.setProjectEnd(staffProjectrequest.getProjectEnd().replace("-", ""));

	        //**  役割
			staffProject.setProjectRole(staffProjectrequest.getProjectRole());
			
	        //**  作成＆更新時間 
			staffProject.setUpdatedAt(now);
			staffProject.setCreatedAt(now);
			
			staffProjectCollection.add(staffProject);
			}
		}
		
		//**  全件データ社員スキル情報に登録
		staffProjectService.saveBatch(staffProjectCollection);
        log.info("【employee_edit】社員プロジェクト情報の登録終了，社員ID：{}", employeeInfoRequest.getId());
    }
    
    /**
     * StaffCategoryから情報は画面に社員のスキル情報を設定
     */
    public void getFromStaffCategory(Long employeeId) {
        try {
            List<StaffCategory> staffCategoryList = staffCategoryService.getbyEmployeeId(employeeId);
            
            List<StaffCategoryRequest> staffCategoryRequestList = new ArrayList<StaffCategoryRequest>();
            for (StaffCategory category : staffCategoryList) {
            	StaffCategoryRequest staffCategoryRequest = new StaffCategoryRequest();
            	
            	//**  技術資格名
            	staffCategoryRequest.setCategoryName(category.getCategoryName());
            	
            	//**  取得年月日
            	String getYmd8 = category.getGetYmd();
            	staffCategoryRequest.setGetYmd(getYmd8.substring(0,4) + "-" + getYmd8.substring(4,6) + "-" + getYmd8.substring(6,8));
            	
            	//**  追加
            	staffCategoryRequestList.add(staffCategoryRequest);
            }
            //**  技術資格情報
            result.put("staffCategoryRequestList", staffCategoryRequestList);
        } catch (Exception e) {
            log.error("【employee_preview】社員技術資格情報の取得エラー: {}", e.getMessage());
            // テーブルが存在しない場合のデフォルト空リストを設定
            result.put("staffCategoryRequestList", new ArrayList<StaffCategoryRequest>());
        }
    }

    
    /**
     * StaffProjectから情報は画面に社員のスキル情報を設定
     */
    public void getFromStaffProject(Long employeeId) {
        try {
            List<StaffProject> staffProjectList = staffProjectService.getbyEmployeeId(employeeId);
            
            List<StaffProjectRequest> staffProjectRequestList = new ArrayList<>();
            for (StaffProject project : staffProjectList) {

            	StaffProjectRequest staffProjectRequest = new StaffProjectRequest();
            	
            	//**  プロジェクト名
            	staffProjectRequest.setProjectName(project.getProjectName());
            	
            	//**  開始年月
            	String strYmd8 = project.getProjectStart();
            	staffProjectRequest.setProjectStart(strYmd8.substring(0,4) + "-" + strYmd8.substring(4,6) + "-" + strYmd8.substring(6,8));
            	
            	//**  終了年月
            	strYmd8 = project.getProjectEnd();
            	staffProjectRequest.setProjectEnd(strYmd8.substring(0,4) + "-" + strYmd8.substring(4,6) + "-" + strYmd8.substring(6,8));
            	
            	//**  プロジェクト役職
            	staffProjectRequest.setProjectRole(project.getProjectRole());
            	
            	//**  追加
            	staffProjectRequestList.add(staffProjectRequest);
            }

            //**  スキル情報
            result.put("staffProjectRequestList", staffProjectRequestList);
        } catch (Exception e) {
            log.error("【employee_preview】社員プロジェクト情報の取得エラー: {}", e.getMessage());
            // テーブルが存在しない場合のデフォルト空リストを設定
            result.put("staffProjectRequestList", new ArrayList<StaffProjectRequest>());
        }
    }

    
    /**
     * Employeeから情報は画面に社員の情報を設定
     */
    public void getFromEmployee(Long employeeId) {

    	//**  Employeeから情報は画面に社員の情報を設定
        Employee employeeInfo = employeeService.getbyEmployeeId(employeeId);
        //Map<String, Object> result = new HashMap<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (employeeInfo == null) {

	        //**  社員ID 
	        String strId = "0000"+ employeeId.toString();
	        strId = strId.substring(strId.length()-4);
	        result.put("employeeId", strId);
	        result.put("employeeStatus", "0");
        }
        else {

	        //**  社員状態 
	        result.put("employeeStatus", "1");
	        
	        //**  社員ID 
	        String strId = "0000"+ employeeInfo.getId().toString();
	        strId = strId.substring(strId.length()-4);
	        result.put("employeeId", strId);
	        
	        //**  支店ID 
	        result.put("branchId", employeeInfo.getBranchId());
	
	        //**  所属部門ID 
	        result.put("departmentId", employeeInfo.getDepartmentId());
	        
	        //**  支店名 
	        result.put("branchName", employeeInfo.getBranchName());
	
	        //**  所属部門名 
	        result.put("departmentName", employeeInfo.getDepartmentName());
	
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
    }
    
    
    /**
     * Staffbasicinfoから情報は画面に社員の情報を設定
     */
    public void getFromStaffbasicinfo(Long employeeId) {
        try {
            //**  Staffbasicinfoから情報は画面に社員の情報を設定

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Staffbasicinfo staffBasicInfo = basicinfoService.getbyEmployeeId(employeeId);
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
        } catch (Exception e) {
            log.error("【employee_preview】社員基本情報の取得エラー: {}", e.getMessage());
            // テーブルが存在しない場合のデフォルト値を設定
            result.put("staffBasicInfoStaus", "0");
        }
    }
    
    /**
     * StaffSkillから情報は画面に社員のスキル情報を設定
     */
    public void getFromStaffSkill(Long employeeId) {
        try {
            List<StaffSkill> staffSkillList = staffSkillService.getbyEmployeeId(employeeId);
            
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
        } catch (Exception e) {
            log.error("【employee_preview】社員スキル情報の取得エラー: {}", e.getMessage());
            // テーブルが存在しない場合のデフォルト空リストを設定
            result.put("staffSkillRequestList", new ArrayList<StaffSkillRequest>());
        }
    }

    
    /**
     * Employeeに社員の情報を登録
     * @throws IOException 
     */
    public void insertIntoEmployee(EmployeeInfoRequest employeeInfoRequest,MultipartFile photoFile) throws IOException {
    
        //**  画面に社員の情報を取得
        Employee employeeInfo = new Employee();
		Date now = new Date();
		
      
        //**  社員の情報かかわる項目の設定
        if (photoFile != null && !photoFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + photoFile.getOriginalFilename();
            Path path = Paths.get(uploadDir, fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, photoFile.getBytes());
            employeeInfo.setPhotoPath(accesdDir + fileName);
        }
		
        //**  社員ID 
		employeeInfo.setId(employeeInfoRequest.getId());

        //**  支店Id 
		employeeInfo.setBranchId(employeeInfoRequest.getBranchId());

        //**  所属部門Id 
		employeeInfo.setDepartmentId(employeeInfoRequest.getDepartmentId());

        //**  従業員名 
		employeeInfo.setName(employeeInfoRequest.getName());

        //**  従業員レベル 
		//employeeInfo.setEmployeeLevel(employeeInfoRequest.getEmployeeLevel());

        //**  メールアドレス 
		employeeInfo.setEmail(employeeInfoRequest.getEmail());

        //**  電話番号 
		employeeInfo.setPhoneNo(employeeInfoRequest.getPhoneNo());
		
        //**  入社年月日 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			employeeInfo.setHireDate(formatter.parse(employeeInfoRequest.getHireDate()));
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

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
		
    }

    
    /**
     * Staffbasicinfoに社員の基本情報を登録
     */
    public void insertIntoStaffbasicinfo(EmployeeInfoRequest employeeInfoRequest) {
    	

		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Staffbasicinfo staffbasicinfo = new Staffbasicinfo();

        //**  社員ID 
		staffbasicinfo.setEmployeeId(employeeInfoRequest.getId());
		
        //**  生年月日 
		try {
			staffbasicinfo.setBirthday(formatter.parse(employeeInfoRequest.getBirthday()));
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
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
    }
    
    
    /**
     * StaffSkillに社員のスキル情報を登録
     */
    public void insertIntoStaffSkill(EmployeeInfoRequest employeeInfoRequest) {
    	
		Date now = new Date();
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
    }
}
