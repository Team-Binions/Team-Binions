package com.beanions.common.dao.admin;

import com.beanions.common.dto.MembersDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AdminMapper {
    List<MembersDTO> membersAllList();

    void modifyOneMember(MembersDTO modify);
}
