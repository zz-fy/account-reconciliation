package cn.zfy.behalf.utils;

import cn.zfy.behalf.config.security.UserAuthInfo;
import cn.zfy.behalf.constants.AppConstants;
import cn.zfy.behalf.constants.TokenKeyConstants;
import cn.zfy.behalf.exception.CustomException;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.util.Base64Utils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @Classname JwtUtils
 * @Description jwt 工具类
 */
public class JwtUtils {


    /**
     * 生成JWT
     *
     * @return
     */
    public static String generateToken(UserAuthInfo userAuthInfo) {
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        return Jwts.builder()
                .claim(TokenKeyConstants.KEY_ID, userAuthInfo.getId())
                .claim(TokenKeyConstants.KEY_USER_NAME, userAuthInfo.getUsername())
                .claim(TokenKeyConstants.KEY_NAME, userAuthInfo.getName())
                .setExpiration(DateUtils.addMinutes(new Date(), AppConstants.TOKEN_EXPIRE_MINUTE))
                .signWith(secretKey)
                .compact();
    }


    /**
     * 获取jwt中的用户信息
     */
    public static UserAuthInfo decode(String jwt) {
        try {
            String infoBase64 = jwt.split("\\.")[1];
            byte[] bytes = Base64Utils.decodeFromString(infoBase64);
            String decodeInfo = new String(bytes, StandardCharsets.UTF_8);
            return JSON.parseObject(decodeInfo, UserAuthInfo.class);
        } catch (Exception e) {
            throw new CustomException("解析JWT异常,获取用户登录信息失败..");
        }
    }

}
