package com.beanions.common.service;

import com.beanions.common.dao.user.UserMapper;
import com.beanions.common.dto.LoginUserDTO;
import com.beanions.common.dto.MembersDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class LoginService {

    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final PasswordEncoder passwordEncoder;

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

    public String findByEmail(String email) {

        String userId = userMapper.findByEmail(email);
        if(!Objects.isNull(userId)) {
            System.out.println("아이디 : " + userId);
            return userId;
        } else {
            System.out.println("전송 fail");
            return null;
        }
    }

    public int findByMemberIdAndEmail(String id,String email) {
        return userMapper.findByMemberIdAndEmail(id,email);
    }

    public int modifyMemberPwd(String id,String tempPwd) {

        int result = 0;
        tempPwd = passwordEncoder.encode(tempPwd);

        result = userMapper.modifyMemberPwd(id,tempPwd);

        if( result > 0 ) {
            System.out.println("Mybatis Mapping Success!");
        } else {
            System.out.println("Mybatis Mapping Fail..");
        }
        return result;
    }
}
