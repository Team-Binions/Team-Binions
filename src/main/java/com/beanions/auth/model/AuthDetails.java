package com.beanions.auth.model;

import com.beanions.common.dto.LoginUserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class AuthDetails implements UserDetails {

    private LoginUserDTO loginUserDTO;

    public AuthDetails(){}

    public AuthDetails(LoginUserDTO loginUserDTO) {
        this.loginUserDTO = loginUserDTO;
    }

    public LoginUserDTO getLoginUserDTO() {
        return loginUserDTO;
    }

    public void setLoginUserDTO(LoginUserDTO loginUserDTO) {
        this.loginUserDTO = loginUserDTO;
    }

    @Override
    public String toString() {
        return "AuthDetails{" +
                "loginUserDTO=" + loginUserDTO +
                '}';
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        System.out.println("getAuthorities() 메서드 동작, 유저의 권한을 대조/조회합니다.");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        loginUserDTO.getRole().forEach(role -> authorities.add(() -> role));
        return authorities;
    }

    @Override
    public String getPassword() {
        System.out.println("getPassword() 메서드 동작, 비밀번호를 권한 대조/조회에 사용하도록 값을 담습니다.");
        System.out.println("권한 조회 비밀번호 : " + loginUserDTO.getMemberPw());

        return loginUserDTO.getMemberPw();
    }

    @Override
    public String getUsername() {
        System.out.println("getUsername() 메서드 동작, 아이디를 권한 대조/조회에 사용하도록 값을 담습니다.");
        System.out.println("권한 조회 아이디 : " + loginUserDTO.getMemberId());
        return loginUserDTO.getMemberId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
