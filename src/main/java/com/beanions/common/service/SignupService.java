package com.beanions.common.service;

import com.beanions.common.dao.signup.SignupMapper;
import com.beanions.common.dto.FilePathDTO;
import com.beanions.common.dto.MembersDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@AllArgsConstructor
@EnableScheduling
public class SignupService {

    @Autowired
    private FilePathDTO filePathDTO;
    private final SignupMapper signupMapper;

    @Scheduled(fixedRate = 300000) // 매 1분마다 실행
    public void deleteFile() {
        Path directory = Path.of("C:\\Lecture\\Team-Beanions\\src\\main\\resources\\assets\\images\\upload\\user\\signupTemp");

        if (directory != null && Files.isDirectory(directory)) {
            try {
                Files.walk(directory)
                        .filter(Files::isRegularFile)
                        .map(Path::toFile)
                        .forEach(File::delete);
                System.out.println("회원가입 이미지 임시저장 파일이 삭제되었습니다...");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error occurred while deleting files.");
            }
        } else {
            System.out.println("Directory not found.");
            File dir = new File(String.valueOf(directory));
            if(!dir.exists()){
                dir.mkdirs();
                System.out.println("file make success!");
            }
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
