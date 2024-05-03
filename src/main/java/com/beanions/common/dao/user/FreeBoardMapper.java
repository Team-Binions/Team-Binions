package com.beanions.common.dao.user;

import com.beanions.common.dto.PostAndMemberDTO;
import com.beanions.common.dto.PostAndMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FreeBoardMapper {
    List<PostAndMemberDTO> yesinAllList();


    List<PostAndMemberDTO> yesinDetail(String id);
}
