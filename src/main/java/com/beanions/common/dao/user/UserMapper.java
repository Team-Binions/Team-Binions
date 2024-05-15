package com.beanions.common.dao.user;
import com.beanions.common.dto.LoginUserDTO;
import com.beanions.common.dto.MembersDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    LoginUserDTO findByMemberId(String username);

    String findByEmail(String email);

    int findByMemberIdAndEmail(String id,String email);

    int modifyMemberPwd(String id, String tempPwd);
}

