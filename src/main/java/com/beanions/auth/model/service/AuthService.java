package com.beanions.auth.model.service;

import com.beanions.auth.model.AuthDetails;
import com.beanions.common.controller.LoginService;
import com.beanions.common.dto.LoginUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private LoginService loginService;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {

        System.out.println("1. loadUserByUsername() 메서드 동작 : " + memberId);
        LoginUserDTO loginUser = loginService.findByMemberId(memberId);
        if(Objects.isNull(loginUser)) {
            throw new UsernameNotFoundException("해당하는 회원 정보가 존재하지 않습니다.");
        }
        System.out.println("3. 의존성 주입 성공 : " + loginUser);
        return new AuthDetails(loginUser);
    }
}
