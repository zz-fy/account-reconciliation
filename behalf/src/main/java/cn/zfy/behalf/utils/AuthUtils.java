package cn.zfy.behalf.utils;

import cn.zfy.behalf.config.security.UserAuthInfo;
import cn.zfy.behalf.constants.AppConstants;
import cn.zfy.behalf.exception.UnauthorizedException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Classname AuthUtils
 * @Description 认证工具类
 */
public class AuthUtils {

    private AuthUtils() {

    }

    public static Long getLoginUserId() {
        UserAuthInfo userAuthInfo = getUserAuthInfo();
        return userAuthInfo.getId();
    }

    public static UserAuthInfo getUserAuthInfo() {
        UserAuthInfo userAuthInfo = JwtUtils.decode(getToken());
        checkUserAuthInfoNull(userAuthInfo);
        return userAuthInfo;
    }

    public static String getToken() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes == null ? null : ((ServletRequestAttributes) requestAttributes).getRequest();
        if (null == request) throw new UnauthorizedException("请求异常:授权失败,请重新登录");
        return request.getHeader(AppConstants.REQUEST_HEAD_TOKEN);
    }

    private static void checkUserAuthInfoNull(UserAuthInfo userAuthInfo) {
        if (Objects.isNull(userAuthInfo)) throw new UnauthorizedException("授权失败,请重新登录");
    }

}
