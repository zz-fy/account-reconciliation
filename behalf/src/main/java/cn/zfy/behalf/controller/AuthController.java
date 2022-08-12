package cn.zfy.behalf.controller;

import cn.zfy.behalf.annotation.IgnoreAuth;
import cn.zfy.behalf.config.security.UserAuthInfo;
import cn.zfy.behalf.constants.AppConstants;
import cn.zfy.behalf.dto.auth.LoginRequest;
import cn.zfy.behalf.dto.auth.ModifyPasswordDto;
import cn.zfy.behalf.entity.response.auth.LoginResponse;
import cn.zfy.behalf.entity.response.auth.UserInfoResponse;
import cn.zfy.behalf.entity.response.base.BaseResponse;
import cn.zfy.behalf.service.AuthService;
import cn.zfy.behalf.service.UserService;
import cn.zfy.behalf.utils.AuthUtils;
import cn.zfy.behalf.utils.RedisUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;

/**
 * @Classname AuthController
 * @Description 认证api
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private AuthService authService;

    /**
     * 登录
     *
     * @param loginRequest
     * @return
     */
    @PostMapping("/login")
    @IgnoreAuth
    public BaseResponse<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        return BaseResponse.ok(authService.login(loginRequest));
    }

    /**
     * 退出登录
     *
     * @return
     */
    @GetMapping("/logout")
    public BaseResponse<?> logout() {
        redisUtils.del(AppConstants.getUserTokenKey(AuthUtils.getLoginUserId()));
        return BaseResponse.ok();
    }

    /**
     * 个人信息
     */
    @GetMapping("info")
    public BaseResponse<UserInfoResponse> getInfo() {
        UserAuthInfo userAuthInfo = AuthUtils.getUserAuthInfo();
        UserInfoResponse response = UserInfoResponse.builder()
                .avatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif")
                .userId(userAuthInfo.getId())
                .username(userAuthInfo.getUsername())
                .name(userAuthInfo.getName())
                .roles(Arrays.asList("admin"))
                .build();
        return BaseResponse.ok(response);
    }

    @PostMapping("/pwd/modify")
    public BaseResponse<?> modifyPassword(@RequestBody @Valid ModifyPasswordDto modifyPasswordDto) {
        authService.modifyPassword(modifyPasswordDto, AuthUtils.getLoginUserId());
        return BaseResponse.ok();
    }


}
