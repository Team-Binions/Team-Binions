package com.beanions.common.dao.signup;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignupMapper {
    int checkDupId(String id);
}
