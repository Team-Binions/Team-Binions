package com.beanions.common.controller;

import com.beanions.common.dto.MailDTO;
<<<<<<< HEAD
=======
import com.beanions.common.dto.MembersDTO;
>>>>>>> parent of 6143ba6 (cache conflict resolve)
import com.beanions.common.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor
public class MainController {

    private final MailService mailService;

    @GetMapping(value = {"/","/main"})
    public String main(){
        return "user/userMain";
    }

    @GetMapping("/signup")
    public String signup(){
        return "user/signup";
    }

    @GetMapping("/mail")
    public void sendEmail(){}

    @PostMapping("/mail")
    public String sendEmail(MailDTO mailDTO) {
        mailService.mailSend(mailDTO);
        return "user/signup";
    }
}
