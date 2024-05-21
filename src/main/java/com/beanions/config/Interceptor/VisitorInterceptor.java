package com.beanions.config.Interceptor;

import com.beanions.user.service.VisitorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//@Component
@AllArgsConstructor
public class VisitorInterceptor implements HandlerInterceptor {

//    @Autowired
//    private final VisitorService visitorService;

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        visitorService.incrementVisitorCount(request);
//
//        int visitorCount = visitorService.getVisitorCount();
//        System.out.println("VisitorCount : " + visitorCount);
//        return true;
//    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        visitorService.incrementVisitorCount(request);
//
//        int visitorCount = visitorService.getVisitorCount();
//        System.out.println("VisitorCount : " + visitorCount);
//    }
    //    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//        visitorService.incrementVisitorCount(request);
//
//        int visitorCount = visitorService.getVisitorCount();
//        System.out.println("VisitorCount : " + visitorCount);
//
//    }
}
