package com.jhc.employee_management.service;

import com.jhc.employee_management.entity.UserLoginInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 30839
* @description 针对表【user_login_info(ログイン情報)】的数据库操作Service
* @createDate 2025-07-06 19:28:49
*/
public interface UserLoginInfoService extends IService<UserLoginInfo> {
    Integer insertInfo(UserLoginInfo userLoginInfo);

    UserLoginInfo getByUsername(String username);

    /**
     * 生成员工id，按顺序
     * @return
     */
    String generateNextEmployeeId();
}
