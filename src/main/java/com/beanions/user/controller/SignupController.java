package com.beanions.user.controller;

import com.beanions.common.dto.MembersDTO;
import com.beanions.user.service.MailService;
import com.beanions.user.service.SignupService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @PostMapping(value = "/request-dupCheck-id")
    @ResponseBody
    public String requestDupCheckId(@RequestBody String id) throws Exception{

        // 문자열로 넘어온 JSON값의 쌍따옴표 제거
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

    @PostMapping(value = "/request-verify-mail")
    @ResponseBody
    public String requestDupCheckEmail(@RequestBody String email) throws Exception{

        email = email.replaceAll("^\"|\"$", "");

        int dupEmail = signupService.checkDupEmail(email);
        System.out.println("이메일 중복 개수 : " + dupEmail);

        if(dupEmail > 0) {
            return new ObjectMapper().writeValueAsString(dupEmail);
        }

        String code = mailService.sendMail(email);
        System.out.println("인증코드 : " + code);

//        //response객체에 json 형태로 인증코드를 저장하는 방법
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonResponse = objectMapper.writeValueAsString(code);
        return new ObjectMapper().writeValueAsString(code);
    }


    @PostMapping(value="/request-verify-wedd")
    @ResponseBody
    public String requestUploadVefFile(@RequestParam(value="file",required = false) MultipartFile file) throws Exception {

        /* 서버로 전달 된 File을 서버에서 설정하는 경로에 저장한다. */
        String root = "src/main/resources/assets/images/upload";
        String filePath = root + "/user/signupTemp";

        File dir = new File(filePath);

        if( !dir.exists() ) {
            dir.mkdirs();
        }

        /* 파일명 변경하기 */
        String originFileName = file.getOriginalFilename();
        System.out.println("originFileName : " + originFileName);
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
//        System.out.println("ext : " + ext);

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
    public ResponseEntity<Object> joinMember(@RequestBody MembersDTO member) {
        // 가입처리
        LocalDateTime now = LocalDateTime.now();
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-EE HH:mm:ss"));

        // 업로드 파일 없으면 바로 회원가입 처리
        if ( member.getWeddingFile() == null ) {
            System.out.println(member);
            signupService.regist(member);
        } else {
            // 파일 저장 로직
            String fileName = member.getWeddingFile();

            String root = "src/main/resources/assets/images/upload";

            String filePath = root + "/user/signupTemp/" + fileName;
            String copyFolderPath = root + "/user/verify/";

            File file = new File(filePath);

            try {
                // 기존 경로 폴더에 있던 파일을(Temp폴더) 해당하는 폴더로 복붙한다.
                Path sourcePath = Paths.get(filePath);
                System.out.println("임시파일 저장 폴더 : " + sourcePath);
                if (!Files.exists(sourcePath)) {
                    // 파일이 존재하지 않는 경우 404 Not Found 응답 반환
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }

                System.out.println(member);
                signupService.regist(member);

                Path destinationPath = Paths.get(copyFolderPath + file.getName());
                Files.move(sourcePath, destinationPath);
                System.out.println("복사된 폴더 경로 : " + destinationPath);

            } catch (IOException e) {
                System.out.println("error : " + e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        System.out.println("===========================================");
        System.out.println("가입 날짜 : " + formatedNow);
        System.out.println(member.getMemberId()+"님 회원가입 완료!");
        System.out.println("===========================================");

        return ResponseEntity.ok().build();
    }

}
