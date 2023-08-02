package com.megazone.springbootboilerplate.config;

import com.megazone.springbootboilerplate.interceptor.LogInterceptor;
import com.megazone.springbootboilerplate.interceptor.MetricInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor()).order(1);
        registry.addInterceptor(new MetricInterceptor()).order(2);
    }
}
