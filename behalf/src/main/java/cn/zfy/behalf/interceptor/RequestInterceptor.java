package cn.zfy.behalf.interceptor;

import cn.zfy.behalf.annotation.IgnoreAuth;
import cn.zfy.behalf.config.security.UserAuthInfo;
import cn.zfy.behalf.constants.AppConstants;
import cn.zfy.behalf.entity.response.base.BaseResponse;
import cn.zfy.behalf.utils.JwtUtils;
import cn.zfy.behalf.utils.RedisUtils;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;

/**
 * 请求拦截器
 */
public class RequestInterceptor implements HandlerInterceptor {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    private RedisUtils redisUtils;


    public RequestInterceptor(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    /**
     * 写入未授权响应
     *
     * @param httpServletRequest  请求对象
     * @param httpServletResponse 响应对象
     */
    public void setUnauthorizedResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String message) {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        try (Writer writer = httpServletResponse.getWriter()) {
            JSON.writeJSONString(writer, BaseResponse.unauthorized(message));
            writer.flush();
        } catch (IOException e) {
            logger.info("Response IOException:{}", e.getMessage());
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) return true;
        //获取控制器对象
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        try {
            //获取方法
            Method method = handlerMethod.getMethod();
            IgnoreAuth ignoreAuth = method.getAnnotation(IgnoreAuth.class);
            if (null != ignoreAuth) return true;
            String token = request.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                this.setUnauthorizedResponse(request, response, "授权失败,请重新登录");
                return false;
            }
            UserAuthInfo userAuthInfo = JwtUtils.decode(token);
            if (null == userAuthInfo) {
                this.setUnauthorizedResponse(request, response, "授权失败,请重新登录");
                return false;
            }
            if (redisUtils.getExpire(AppConstants.getUserTokenKey(userAuthInfo.getId())) < 0) {
                this.setUnauthorizedResponse(request, response, "会话过期,请重新登录");
                return false;
            }
            return true;
        } catch (Exception e) {
            this.setUnauthorizedResponse(request, response, "授权异常,请重新登录");
            return false;
        }
    }
}