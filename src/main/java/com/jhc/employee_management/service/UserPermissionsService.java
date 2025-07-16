package com.jhc.employee_management.service;

import com.jhc.employee_management.entity.UserPermissions;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 30839
* @description 针对表【user_permissions(ユーザー権限テーブル)】的数据库操作Service
* @createDate 2025-07-06 19:28:49
*/
public interface UserPermissionsService extends IService<UserPermissions> {
    /**
     * 根据员工id取得
     * @param employeeId
     * @return
     */
    UserPermissions getByEmployeeId(String employeeId);
}
