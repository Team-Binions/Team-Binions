package com.beanions.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public void login(){}

    @GetMapping("/fail")
    public ModelAndView loginFailed(ModelAndView mv, @RequestParam String message) {

        mv.addObject("message",message);
        mv.setViewName("/auth/fail");

        return mv;
    }

    @GetMapping("/forgetInfo")
    public ModelAndView forgetInfo(ModelAndView mv){

        return mv;
    }

}
