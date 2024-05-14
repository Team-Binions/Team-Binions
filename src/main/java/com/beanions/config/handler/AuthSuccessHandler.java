package com.beanions.config.handler;

import com.beanions.auth.model.AuthDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

@Configuration
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        AuthDetails dto =
                (AuthDetails) authentication.getPrincipal();

        // 인증 된 유저 정보를 세션으로 등록
        HttpSession session = request.getSession();
        System.out.println("==============인증 된 유저정보 세션 등록==============");
        session.setAttribute("memberCode", dto.getLoginUserDTO().getMemberCode());
        session.setAttribute("memberId", dto.getLoginUserDTO().getMemberId());
        session.setAttribute("memberPw", dto.getLoginUserDTO().getMemberPw());
        session.setAttribute("nickname", dto.getLoginUserDTO().getNickname());
        session.setAttribute("email", dto.getLoginUserDTO().getEmail());
        session.setAttribute("phone", dto.getLoginUserDTO().getPhone());
        session.setAttribute("gender", dto.getLoginUserDTO().getGender());
        session.setAttribute("marriedStatus", dto.getLoginUserDTO().getMarriedStatus());
        session.setAttribute("weddingFile", dto.getLoginUserDTO().getWeddingFile());
        session.setAttribute("weddingVerified", dto.getLoginUserDTO().getWeddingVerified());
        session.setAttribute("signupDate", dto.getLoginUserDTO().getSignupDate());
        System.out.println("=================================================");

        Enumeration<String> sessionNames = session.getAttributeNames();
        while (sessionNames.hasMoreElements()) {
            System.out.println("remain : " + sessionNames.nextElement());
        }

        System.out.println("========로그인 성공시 권한을 조회해 리다이렉트 url 매핑=======");
        System.out.println("인증 된 권한 =>" + dto);
        //권한리스트를 추출
        Collection<GrantedAuthority> authlist = dto.getAuthorities();
        Iterator<GrantedAuthority> authlist_it= authlist.iterator();
        System.out.println("=======================================================");

        String url="/";

        while(authlist_it.hasNext()) {
            GrantedAuthority authority= authlist_it.next();
            //설정되어 있는 권한 중 ROLE_ADMIN이 있다면
            if(authority.getAuthority().equals("ADMIN")) {
                url="/admin";
            }
        }

        response.sendRedirect(url);
    }
}
