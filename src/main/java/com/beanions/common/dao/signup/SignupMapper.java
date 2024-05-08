package com.beanions.common.dao.signup;

import com.beanions.common.dto.MembersDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignupMapper {
    int checkDupId(String id);

    int checkDupNkname(String nkname);

    int joinMember(MembersDTO member);
}
