package cn.zfy.behalf.config.security;

import cn.zfy.behalf.interceptor.RequestInterceptor;
import cn.zfy.behalf.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * @Classname WebMvcConfiguration
 * @Description web配置
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private static final List<String> EXCLUDE_PATH = Arrays.asList("/", "/swagger-ui.html", "/error/**", "/swagger-resources/**", "/webjars/**");

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor(redisUtils))
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATH);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }

}
