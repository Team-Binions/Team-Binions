package com.beanions.auth.controller;

import com.beanions.common.dto.LoginUserDTO;
import com.beanions.auth.model.service.LoginService;
import com.beanions.user.service.MailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final LoginService loginService;
    private final MailService mailService;

    @GetMapping("/login")
    public String login(Authentication authentication, Model model){

        if( authentication != null ) {
            return "redirect:/auth/logout";
        }
        return "/auth/login";
    }

    @GetMapping("/fail")
    public ModelAndView loginFailed(ModelAndView mv, @RequestParam String message) {

        mv.addObject("message",message);
        mv.setViewName("/auth/fail");

        return mv;
    }

    @GetMapping("/forgetInfo")
    public ModelAndView forgetInfo(ModelAndView mv,@RequestParam int no){
        mv.addObject("no",no);
        return mv;
    }

    @PostMapping(value = "/request-forget-verify-mail")
    @ResponseBody
    public String requestCheckValidEmail(@RequestBody String email) throws Exception{

        email = email.replaceAll("^\"|\"$", "");

        String userId = loginService.findByEmail(email);
        System.out.println("유저 아이디 : " + userId);

        if(userId != null) {
            String code = mailService.sendMail(email);
            System.out.println("인증코드 : " + code);
            return new ObjectMapper().writeValueAsString(code);
        }
        return new ObjectMapper().writeValueAsString(null);
    }

    @PostMapping("/request-checkValid-mail")
    @ResponseBody
    public Object checkValidIdByEmail(@RequestBody String email) {

        email = email.replaceAll("^\"|\"$", "");

        String userId = loginService.findByEmail(email);

        if( userId != null ) {
            mailService.sendMail(email,userId);
            return ResponseEntity.ok();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(value = "/request-checkValid-id-and-email")
    @ResponseBody
    public String checkValidIdAndEmail(@RequestBody LoginUserDTO member) throws Exception{
        String id = member.getMemberId();
        System.out.println(id);
        String email = member.getEmail();
        System.out.println(email);
        String tempPwd = "@temp123";

        int validMember = loginService.findByMemberIdAndEmail(id,email);

        if( validMember > 0 ) {
            int modifyPwd = loginService.modifyMemberPwd(id,tempPwd);
            if (modifyPwd > 0) {
                mailService.sendPwdMail(email,tempPwd);
                return new ObjectMapper().writeValueAsString(modifyPwd);
            } else {
                return new ObjectMapper().writeValueAsString(modifyPwd);
            }
        }
        return new ObjectMapper().writeValueAsString(validMember);
    }

}
