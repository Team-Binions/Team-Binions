package com.beanions.common.controller;

import com.beanions.common.service.MailService;
import com.beanions.common.service.SignupService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class MainController {

    private final MailService mailService;
    private final SignupService signupService;

    @GetMapping(value = {"/","/main"})
    public String main(){
        return "user/userMain";
    }

    @GetMapping("/signup")
    public String signup(){
        return "user/signup";
    }

    @PostMapping(value = "/request-verify-mail")
    @ResponseBody
    public String requestSignUp(@RequestBody String email) throws Exception{

        String code = mailService.sendMail(email);
        System.out.println("인증코드 : " + code);

//        //response객체에 json 형태로 인증코드를 저장하는 방법
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonResponse = objectMapper.writeValueAsString(code);

        return new ObjectMapper().writeValueAsString(code);
    }

    @PostMapping(value = "/request-dupCheck-id")
    @ResponseBody
    public String requestDupCheckId(@RequestBody String id) throws Exception{

        // 문자열로 넘어온 값의 쌍따옴표 제거
        id = id.replaceAll("^\"|\"$", "");

        int count = signupService.checkDupId(id);

        System.out.println("id : " + id);
        System.out.println("count : " + count);

        return new ObjectMapper().writeValueAsString(count);
    }

}
