package com.fanyi.assistant.config;

import com.fanyi.assistant.Interceptor.LoginInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author FanYi
 * @Date 2019/8/15 12:15
 * @Version 1.0.1
 **/
@SpringBootConfiguration
public class LoginAdapter implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login", "/common/**",
                "/assets/**", "/getVerifyCode", "/checkUser", "/registerUser");
    }
}