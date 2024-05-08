package com.beanions.common.service;

import com.beanions.common.dao.signup.SignupMapper;
import com.beanions.common.dto.MembersDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignupService {

    private final SignupMapper signupMapper;

    public int checkDupId(String id) {
        return signupMapper.checkDupId(id);
    }

    public int checkDupNkname(String nkname) {
        return signupMapper.checkDupNkname(nkname);
    }

    public int regist(MembersDTO member) {
        return signupMapper.joinMember(member);
    }
}
