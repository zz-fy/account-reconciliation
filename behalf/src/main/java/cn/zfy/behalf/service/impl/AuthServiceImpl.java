package cn.zfy.behalf.service.impl;

import cn.zfy.behalf.config.security.UserAuthInfo;
import cn.zfy.behalf.constants.AppConstants;
import cn.zfy.behalf.dto.auth.LoginRequest;
import cn.zfy.behalf.dto.auth.ModifyPasswordDto;
import cn.zfy.behalf.entity.po.User;
import cn.zfy.behalf.entity.response.auth.LoginResponse;
import cn.zfy.behalf.exception.CustomException;
import cn.zfy.behalf.service.AuthService;
import cn.zfy.behalf.service.UserService;
import cn.zfy.behalf.utils.JwtUtils;
import cn.zfy.behalf.utils.RedisUtils;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Resource
    private RedisUtils redisUtils;

    @Resource
    private UserService userService;


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userService.getByUserName(loginRequest.getUsername());
        if (Objects.isNull(user)) throw new CustomException("未知用户");
        String password = new String(Base64Utils.decodeFromString(loginRequest.getPassword()));
        if (!bCryptPasswordEncoder.matches(password, user.getPassword()))
            throw new CustomException("密码错误");
        UserAuthInfo userAuthInfo = UserAuthInfo.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .build();
        String jwt = JwtUtils.generateToken(userAuthInfo);
        //将token存入redis
        redisUtils.set(AppConstants.getUserTokenKey(user.getId()), jwt, AppConstants.TOKEN_EXPIRE_MINUTE * 60L);
        return new LoginResponse(jwt);
    }


    @Override
    public void modifyPassword(ModifyPasswordDto modifyPasswordDto, Long userId) {
        User user = Optional.of(userService.getById(userId)).orElseThrow(() -> new CustomException("未知用户"));
        String oldPwd = new String(Base64Utils.decodeFromString(modifyPasswordDto.getOldPassword()));
        if (!bCryptPasswordEncoder.matches(oldPwd, user.getPassword()))
            throw new CustomException("原始密码不对");
        String newPwd = new String(Base64Utils.decodeFromString(modifyPasswordDto.getNewPassword()));
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId, userId)
                .set(User::getPassword, bCryptPasswordEncoder.encode(newPwd));
        userService.update(updateWrapper);
    }
}
