package com.beanions.common.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@AllArgsConstructor
public class MailService {

    private JavaMailSender javaMailSender;
    private static String number;
    private static final String FROM_ADDRESS = "lmcwul@gmail.com";

    public static void createNumber(){
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for(int i=0; i<8; i++) { // 총 8자리 인증 번호 생성
            int idx = random.nextInt(3); // 0~2 사이의 값을 랜덤하게 받는다.

            // 0,1,2 값을 switchcase를 통해 꼬아버린다.
            // 숫자와 ASCII 코드를 이용한다.
            switch (idx) {
                case 0 :
                    // 0일 때, a~z 까지 랜덤 생성 후 key에 추가
                    key.append((char) (random.nextInt(26) + 97));
                    break;
                case 1:
                    // 1일 때, A~Z 까지 랜덤 생성 후 key에 추가
                    key.append((char) (random.nextInt(26) + 65));
                    break;
                case 2:
                    // 2일 때, 0~9 까지 랜덤 생성 후 key에 추가
                    key.append(random.nextInt(9));
                    break;
            }
        }
        number = key.toString();
    }

    public MimeMessage createMessage(String email){
        createNumber();
        log.info("Number : {}",number);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true); // Helper 사용
            messageHelper.setFrom(FROM_ADDRESS);
            messageHelper.setTo(email);
            messageHelper.setSubject("[예랑예신] 이메일 인증 번호 발송");

            String body = "<html><body style='background-color: #000000 !important; margin: 0 auto; max-width: 600px; word-break: break-all; padding-top: 50px; color: #ffffff;'>";
            body += "<h1 style='padding-top: 20px; font-size: 30px;'>이메일 주소 인증</h1>";
            body += "<p style='padding-top: 20px; padding-left: 20px; font-size: 18px; opacity: 0.6; line-height: 30px; font-weight: 400;'>안녕하세요? 예랑예신 관리자 입니다.<br />";
            body += "예랑예신 서비스 사용을 위해 회원가입시 고객님께서 입력하신 이메일 주소의 인증이 필요합니다.<br />";
            body += "하단의 인증 번호로 이메일 인증을 완료하시면, 정상적으로 예랑예신 커뮤니티를 이용하실 수 있습니다.<br />";
            body += "항상 최선의 노력을 다하는 예랑예신이 되겠습니다.<br />";
            body += "감사합니다.</p>";
            body += "<div class='code-box' style='margin-top: 50px; padding-top: 20px; color: #000000; padding-bottom: 20px; font-size: 25px; text-align: center; background-color: #f4f4f4; border-radius: 10px;'>" + number + "</div>";
            body += "<div style='text-align: center; padding-top: 20px;'><img class='logo' src='cid:image' alt='예랑예신메인로고'><div>";
            body += "</body></html>";
            messageHelper.setText(body, true);
            ClassPathResource image = new ClassPathResource("assets/images/common/logo_jpg.jpg");
            messageHelper.addInline("image", image);
        }catch (MessagingException e){
            e.printStackTrace();
        }
        return mimeMessage;
    }

    public String sendMail(String email) {
        MimeMessage mimeMessage = createMessage(email);
        log.info("[Mail 전송 시작]");
        javaMailSender.send(mimeMessage);
        log.info("[Mail 전송 완료]");
        return number;
    }
}
