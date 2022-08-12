package cn.zfy.behalf.entity.response.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Classname LoginResponse
 * @Description 登录响应
 */
@Data
@AllArgsConstructor
public class LoginResponse {

    private String token;

}
