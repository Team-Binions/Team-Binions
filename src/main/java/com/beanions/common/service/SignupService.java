package com.beanions.common.service;

import com.beanions.common.dao.signup.SignupMapper;
import com.beanions.common.dto.MembersDTO;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
@EnableScheduling
public class SignupService {

    private final SignupMapper signupMapper;

    @Scheduled(fixedRate = 300000) // ms 기준 / 매번 약 5분마다 실행
    public void deleteFile() {
        Path directory = Path.of("src/main/resources/assets/images/upload/user/signupTemp");

        if (directory != null && Files.isDirectory(directory)) {
            try {
                LocalDateTime now = LocalDateTime.now();
                String formatedNow = now.format(DateTimeFormatter.ofPattern("MMM dd EEE HH:mm:ss yyyy"));

                Files.walk(directory)
                        .filter(Files::isRegularFile)
                        .map(Path::toFile)
                        .forEach(File::delete);
                System.out.println("회원가입 이미지 임시저장 파일이 삭제되었습니다..." + formatedNow);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error occurred while deleting files.");
            }
        } else {
            System.out.println("Directory not found.");
        }
    }

    public int checkDupId(String id) {
        return signupMapper.checkDupId(id);
    }

    public int checkDupNkname(String nkname) {
        return signupMapper.checkDupNkname(nkname);
    }

    public void regist(MembersDTO member) {
        signupMapper.joinMember(member);
    }

    public int checkDupEmail(String email) {
        return signupMapper.checkDupEmail(email);
    }
}
