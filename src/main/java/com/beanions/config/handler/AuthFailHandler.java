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
            errorMessage = "no-match";
        } else if ( exception instanceof InternalAuthenticationServiceException ) {
            errorMessage = "error_s";
        } else if ( exception instanceof UsernameNotFoundException ) {
            errorMessage = "no-id";
        } else if ( exception instanceof AuthenticationCredentialsNotFoundException ) {
            // 보안 컨텍스트에 인증 객체가 존재하지 않거나, 인증 정보가 없는 생태에서 보안처리된 리소스에 접근하는 경우 발생
            errorMessage = "reject";
        } else {
            errorMessage = "error_n";
        }

        // URL을 안전하게 인코딩
        errorMessage = URLEncoder.encode(errorMessage,"UTF-8");

        // 오류를 처리할 페이지(뷰) 설정
        setDefaultFailureUrl("/fail?message=" + errorMessage);

        super.onAuthenticationFailure(request, response, exception);
    }
}
