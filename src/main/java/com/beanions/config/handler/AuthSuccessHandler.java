package com.beanions.config.handler;

import com.beanions.auth.model.AuthDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Configuration
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        AuthDetails dto =
                (AuthDetails) authentication.getPrincipal();
        System.out.println("인증후=>"+dto);
        //권한리스트를 추출
        Collection<GrantedAuthority> authlist = dto.getAuthorities();
        Iterator<GrantedAuthority> authlist_it= authlist.iterator();
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
