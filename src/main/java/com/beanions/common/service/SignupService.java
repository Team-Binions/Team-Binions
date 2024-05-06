package com.beanions.common.service;

import com.beanions.common.dao.signup.SignupMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class SignupService {

    private final SignupMapper signupMapper;

    public int checkDupId(String id) {
        return signupMapper.checkDupId(id);
    }
}
