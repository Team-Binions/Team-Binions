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
        System.out.println("6. getAuthorities() 메서드 동작");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        loginUserDTO.getRole().forEach(role -> authorities.add(() -> role));
        return authorities;
    }

    @Override
    public String getPassword() {
        System.out.println("4. getPassword() 메서드 동작 : " + loginUserDTO.getPassword());
        return loginUserDTO.getPassword();
    }

    @Override
    public String getUsername() {
        System.out.println("5. getUsername() 메서드 동작 : " + loginUserDTO.getMemberId());
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
