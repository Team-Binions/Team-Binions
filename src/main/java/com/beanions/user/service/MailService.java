package com.beanions.user.service;

import com.beanions.user.dto.EmailDTO;
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

            String body = "<html><body><div style='width: 100%; height: 100%; display: flex; flex-direction: column; justify-content: center; align-items: center; background: center;'>";
            body += "<div style='width: 45%; max-width: 444px; padding: 40px; background-color: #fff; border: solid 2px; border-color: #6667AB; border-radius:20px; box-shadow: 0 2px 5px rgba(102, 103, 171, 0.2);'>";
            body += "<h1 style='padding-top: 20px; font-size: 30px;'>이메일 주소 인증</h1>";
            body += "<p style='padding-top: 20px; padding-left: 20px; font-size: 18px; opacity: 0.6; line-height: 30px; font-weight: 400;'>안녕하세요? 예랑예신 관리자 입니다.<br />";
            body += "예랑예신 서비스 사용을 위해 회원가입시 고객님께서 입력하신 이메일 주소의 인증이 필요합니다.<br />";
            body += "하단의 인증 번호로 이메일 인증을 완료하시면, 정상적으로 예랑예신 커뮤니티를 이용하실 수 있습니다.<br />";
            body += "항상 최선의 노력을 다하는 예랑예신이 되겠습니다.<br />";
            body += "감사합니다.</p>";
            body += "<div class='code-box' style='margin-top: 50px; padding-top: 20px; color: #000000; padding-bottom: 20px; font-size: 25px; text-align: center; background-color: #f4f4f4; border-radius: 10px;'>" + number + "</div>";
            body += "<div style='text-align: center; padding-top: 20px;'><img class='logo' src='cid:image' alt='예랑예신메인로고'><div>";
            body += "</div></div></body></html>";
            messageHelper.setText(body, true);
            ClassPathResource image = new ClassPathResource("assets/images/common/logo_jpg.jpg");
            messageHelper.addInline("image", image);
        }catch (MessagingException e){
            e.printStackTrace();
        }
        return mimeMessage;
    }

    public MimeMessage createMessage(String email,String userId){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true); // Helper 사용
            messageHelper.setFrom(FROM_ADDRESS);
            messageHelper.setTo(email);
            messageHelper.setSubject("[예랑예신] 이메일 인증 아이디 발송");

            String body = "<html><body><div style='width: 100%; height: 100%; display: flex; flex-direction: column; justify-content: center; align-items: center; background: center;'>";
            body += "<div style='width: 45%; max-width: 444px; padding: 40px; background-color: #fff; border: solid 2px; border-color: #6667AB; border-radius:20px; box-shadow: 0 2px 5px rgba(102, 103, 171, 0.2);'>";
            body += "<h1 style='padding-top: 20px; font-size: 30px;'>이메일 주소 인증 아이디</h1>";
            body += "<p style='padding-top: 20px; padding-left: 20px; font-size: 18px; opacity: 0.6; line-height: 30px; font-weight: 400;'>안녕하세요? 예랑예신 관리자 입니다.<br />";
            body += "인증 확인 안내 회신입니다.<br />";
            body += "회원님의 아이디는</p>";
            body += "<div class='code-box' style='padding-top: 20px; color: #000000; padding-bottom: 20px; font-size: 25px; text-align: center; background-color: #f4f4f4; border-radius: 10px;'>" + userId + "</div>";
            body += "<p style='padding-top: 20px; padding-left: 20px; font-size: 18px; opacity: 0.6; line-height: 30px; font-weight: 400;'>입니다.<br />";
            body += "항상 최선의 노력을 다하는 예랑예신이 되겠습니다.<br />";
            body += "감사합니다.</p>";
            body += "<div style='text-align: center; padding-top: 20px;'><img class='logo' src='cid:image' alt='예랑예신메인로고'><div>";
            body += "</div></div></body></html>";
            messageHelper.setText(body, true);
            ClassPathResource image = new ClassPathResource("assets/images/common/logo_jpg.jpg");
            messageHelper.addInline("image", image);
        }catch (MessagingException e){
            e.printStackTrace();
        }
        return mimeMessage;
    }



    private MimeMessage createPwdMessage(String email, String tempPwd) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true); // Helper 사용
            messageHelper.setFrom(FROM_ADDRESS);
            messageHelper.setTo(email);
            messageHelper.setSubject("[예랑예신] 임시 비밀번호 발송");

            String body = "<html><body><div style='width: 100%; height: 100%; display: flex; flex-direction: column; justify-content: center; align-items: center; background: center;'>";
            body += "<div style='width: 45%; max-width: 444px; padding: 40px; background-color: #fff; border: solid 2px; border-color: #6667AB; border-radius:20px; box-shadow: 0 2px 5px rgba(102, 103, 171, 0.2);'>";
            body += "<h1 style='padding-top: 20px; font-size: 30px;'>임시 비밀번호</h1>";
            body += "<p style='padding-top: 20px; padding-left: 20px; font-size: 18px; opacity: 0.6; line-height: 30px; font-weight: 400;'>안녕하세요? 예랑예신 관리자 입니다.<br />";
            body += "인증 확인 안내 회신입니다.<br />";
            body += "회원님의 임시 비밀번호는</p>";
            body += "<div class='code-box' style='padding-top: 20px; color: #000000; padding-bottom: 20px; font-size: 25px; text-align: center; background-color: #f4f4f4; border-radius: 10px;'>" + tempPwd + "</div>";
            body += "<p style='padding-top: 20px; padding-left: 20px; font-size: 18px; opacity: 0.6; line-height: 30px; font-weight: 400;'>입니다.<br />";
            body += "항상 최선의 노력을 다하는 예랑예신이 되겠습니다.<br />";
            body += "감사합니다.</p>";
            body += "<div style='text-align: center; padding-top: 20px;'><img class='logo' src='cid:image' alt='예랑예신메인로고'><div>";
            body += "</div></div></body></html>";
            messageHelper.setText(body, true);
            ClassPathResource image = new ClassPathResource("assets/images/common/logo_jpg.jpg");
            messageHelper.addInline("image", image);
        }catch (MessagingException e){
            e.printStackTrace();
        }
        return mimeMessage;
    }

    public MimeMessage createMessage(EmailDTO email){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true); // Helper 사용
            messageHelper.setFrom(FROM_ADDRESS);
            messageHelper.setTo(FROM_ADDRESS);
            messageHelper.setSubject("[예랑예신] 고객 문의 메일");

            String body = "<html><body><div style='width: 100%; height: 100%; display: flex; flex-direction: column; justify-content: center; align-items: center; background: center;'>";
            body += "<div style='width: 45%; max-width: 444px; padding: 40px; background-color: #fff; border: solid 2px; border-color: #6667AB; border-radius:20px; box-shadow: 0 2px 5px rgba(102, 103, 171, 0.2);'>";
            body += "<h1 style='padding-top: 20px; font-size: 30px;'>고객 문의 메일</h1>";
            body += "<p style='padding-top: 20px; padding-left: 20px; font-size: 18px; opacity: 0.6; line-height: 30px; font-weight: 400;'>";
            body += "<b>" + email.getEmail() + "<b> 로부터 온 메일입니다.<br/><hr/>";
            body += email.getContext() + "</p>";
            body += "<div style='text-align: center; padding-top: 20px;'><img class='logo' src='cid:image' alt='예랑예신메인로고'><div>";
            body += "</div></div></body></html>";
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

    public void sendMail(String email,String userId) {
        MimeMessage mimeMessage = createMessage(email,userId);
        log.info("[Mail 전송 시작]");
        javaMailSender.send(mimeMessage);
        log.info("[Mail 전송 완료]");
    }

    public void sendPwdMail(String email, String tempPwd) {
        MimeMessage mimeMessage = createPwdMessage(email,tempPwd);
        log.info("[Mail 전송 시작]");
        javaMailSender.send(mimeMessage);
        log.info("[Mail 전송 완료]");
    }

    public void sendMail(EmailDTO email) {
        MimeMessage mimeMessage = createMessage(email);
        log.info("[Mail 전송 시작]");
        javaMailSender.send(mimeMessage);
        log.info("[Mail 전송 완료]");
    }
}
