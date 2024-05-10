package com.beanions.common.controller;

import com.beanions.common.dto.MembersDTO;
import com.beanions.common.service.MailService;
import com.beanions.common.service.SignupService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class MainController {

    @GetMapping(value = {"/","/main"})
    public String main(){
        return "user/main";
    }

    // feature/adminPost
    @GetMapping("/admin")
    public String admin() {
        return "admin/main/dashBoard";
    }

}
