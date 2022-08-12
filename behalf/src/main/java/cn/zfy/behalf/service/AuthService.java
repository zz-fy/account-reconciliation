package cn.zfy.behalf.service;

import cn.zfy.behalf.dto.auth.LoginRequest;
import cn.zfy.behalf.dto.auth.ModifyPasswordDto;
import cn.zfy.behalf.entity.response.auth.LoginResponse;

public interface AuthService {

    /**
     * 登录
     *
     * @param loginRequest
     * @return
     */
    LoginResponse login(LoginRequest loginRequest);


    /**
     * 修改密码
     *
     * @param modifyPasswordDto
     * @param userId
     */
    void modifyPassword(ModifyPasswordDto modifyPasswordDto, Long userId);

}
