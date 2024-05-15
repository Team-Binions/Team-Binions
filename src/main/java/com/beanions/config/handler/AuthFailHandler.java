package com.beanions.config.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;
import java.net.URLEncoder;

/*
    사용자의 로그인 실패 시,
    실패 요청을 커스텀 하기위한 핸들러
 */
@Configuration
public class AuthFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String errorMessage = "";

        if( exception instanceof BadCredentialsException ) {
            errorMessage = "아이디가 존재하지 않거나 비밀번호가 일치하지 않습니다.";
        } else if ( exception instanceof InternalAuthenticationServiceException ) {
            errorMessage = "서버에서 오류가 발생하였습니다. 관리자에게 문의해주세요.";
        } else if ( exception instanceof UsernameNotFoundException ) {
            errorMessage = "존재하지 않는 ID입니다.";
        } else if ( exception instanceof AuthenticationCredentialsNotFoundException ) {
            // 보안 컨텍스트에 인증 객체가 존재하지 않거나, 인증 정보가 없는 생태에서 보안처리된 리소스에 접근하는 경우 발생
            errorMessage = "인증 요청이 거부되었습니다.";
        } else {
            errorMessage = "알 수 없는 오류로 로그인 요청을 처리할 수 없습니다. 관리자에게 문의해주세요.";
        }

        // URL을 안전하게 인코딩
        errorMessage = URLEncoder.encode(errorMessage,"UTF-8");

        // 오류를 처리할 페이지(뷰) 설정
        setDefaultFailureUrl("/auth/fail?message=" + errorMessage);

        super.onAuthenticationFailure(request, response, exception);
    }
}
