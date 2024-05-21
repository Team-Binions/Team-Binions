package com.beanions.config;

import com.beanions.auth.dao.UserMapper;
import com.beanions.config.Interceptor.VisitorInterceptor;
import com.beanions.user.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/assets/images/", "file:src/main/resources/assets/images/");

    }
}
