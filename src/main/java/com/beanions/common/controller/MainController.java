package com.beanions.common.controller;

import com.beanions.auth.model.AuthDetails;
import com.beanions.common.dto.MembersDTO;
import com.beanions.common.service.MailService;
import com.beanions.common.service.SignupService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class MainController{

    @GetMapping(value = {"/","/main"})
    public String main(Authentication authentication){

        if( authentication != null ) {
            AuthDetails dto = (AuthDetails) authentication.getPrincipal();
            Collection<GrantedAuthority> authlist = dto.getAuthorities();
            Iterator<GrantedAuthority> authlist_it= authlist.iterator();
            while(authlist_it.hasNext()) {
                GrantedAuthority authority= authlist_it.next();
                //설정되어 있는 권한 중 ROLE_ADMIN이 있다면
                if(authority.getAuthority().equals("ADMIN")) {
                    return "redirect:/auth/logout";
                }
            }
        }
        return "user/main";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

}
