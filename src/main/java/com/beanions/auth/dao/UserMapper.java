package com.beanions.auth.dao;
import com.beanions.common.dto.LoginUserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    LoginUserDTO findByMemberId(String username);

    String findByEmail(String email);

    int findByMemberIdAndEmail(String id,String email);

    int modifyMemberPwd(String id, String tempPwd);
}

