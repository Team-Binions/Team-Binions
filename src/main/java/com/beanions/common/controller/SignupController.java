package com.beanions.common.controller;

import com.beanions.common.dto.MembersDTO;
import com.beanions.common.service.MailService;
import com.beanions.common.service.SignupService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class SignupController {

    private final MailService mailService;
    private final SignupService signupService;

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

//        System.out.println("id : " + id);
//        System.out.println("count : " + count);

        return new ObjectMapper().writeValueAsString(count);
    }

    @PostMapping(value="/request-dupCheck-nickname")
    @ResponseBody
    public String requestDupCheckNkname(@RequestBody String nkname) throws Exception {
        nkname = nkname.replaceAll("^\"|\"$", "");

        int count = signupService.checkDupNkname(nkname);

        return new ObjectMapper().writeValueAsString(count);
    }

    @PostMapping(value="/request-verify-wedd")
    @ResponseBody
    public String requestUploadVefFile(@RequestParam(value="file",required = false) MultipartFile file) throws Exception {

        /* 서버로 전달 된 File을 서버에서 설정하는 경로에 저장한다. */
        String root = "src/main/resources/assets/images/upload";
        String filePath = root + "/user/verify";

        File dir = new File(filePath);

        if( !dir.exists() ) {
            dir.mkdirs();
        }

        /* 파일명 변경하기 */
        String originFileName = file.getOriginalFilename();
        System.out.println("originFileName : " + originFileName);
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        System.out.println("ext : " + ext);

        String savedName = UUID.randomUUID() + ext;
        System.out.println("savedName : " + savedName);

        /* 파일 저장 */
        try {
            Path path= Paths.get(filePath + "/" + savedName).toAbsolutePath();
            file.transferTo(path.toFile());
//            file.transferTo(new File(filePath + "/" + savedName));

        } catch (IOException e) {
            System.out.println("error : " + e);
        }

        return new ObjectMapper().writeValueAsString(savedName);
    }

    @PostMapping(value = "/request-join-member")
    public String joinMember(Model model, @RequestBody MembersDTO member) {
        System.out.println(member);
        int result = signupService.regist(member);

        String message = "";

        if( result > 0 ) {
            message = "회원가입이 정상적으로 완료되었습니다.";
        } else {
            message = "회원가입이 실패하였습니다.";
        }

        model.addAttribute("message",message);

        return "redirect:/main";
    }

}
