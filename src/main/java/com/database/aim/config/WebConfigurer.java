package com.database.aim.config;

import com.database.aim.interceptor.LoginInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.*;

@SpringBootConfiguration
public class WebConfigurer implements WebMvcConfigurer {
    @Bean
    public LoginInterceptor getLoginInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }
}