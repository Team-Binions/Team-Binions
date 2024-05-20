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

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) { // IOC 컨테이너가 가지고있는 객체이다.
//
//        registry.addInterceptor(new VisitorInterceptor(new VisitorService()))
//                .addPathPatterns("/**")
//                /*
//                    static 하위의 정적 리소스는 인터셉터가 적용되지 않도록 한다.
//                    path를 전부로 지정해줬기 때문에.
//                 */
//                .excludePathPatterns("/assets/**")
//                .excludePathPatterns("/mapper/**")
//                /*
//                    /error로 포워딩되는 경로도 제외해주어야 한다.
//                 */
//                .excludePathPatterns("/error/*")
//                .excludePathPatterns("/admin/**");
//
//    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/assets/images/", "file:src/main/resources/assets/images/");

    }
}
