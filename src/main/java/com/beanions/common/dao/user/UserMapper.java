package com.beanions.common.dao.user;
import com.beanions.common.dto.LoginUserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    LoginUserDTO findByMemberId(String username);
}

