package com.beanions.auth.dao;
import com.beanions.common.dto.LoginUserDTO;
import com.beanions.common.dto.MembersDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    LoginUserDTO findByMemberId(String username);

    String findByEmail(String email);

    int findByMemberIdAndEmail(String id,String email);

    int modifyMemberPwd(String id, String tempPwd);


    int checkDupId(String id);

    int checkDupNkname(String nkname);

    void joinMember(MembersDTO member);

    int checkDupEmail(String email);

//    void insertVisitorCount(Integer visitorCount);
}

