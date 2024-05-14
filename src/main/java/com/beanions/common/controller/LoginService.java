package com.beanions.common.controller;

import com.beanions.common.dao.user.UserMapper;
import com.beanions.common.dto.LoginUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginService {

    @Autowired
    private UserMapper userMapper;

    public LoginUserDTO findByMemberId(String memberId) {
        System.out.println("2.Mybatis 전송 : " + memberId);
        LoginUserDTO loginUser = userMapper.findByMemberId(memberId);
        if(!Objects.isNull(loginUser)) {
            return loginUser;
        } else {
            System.out.println("전송 fail");
            return null;
        }
    }
}
