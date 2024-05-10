package com.beanions.common.dao.user.board;

import com.beanions.common.dto.*;
import com.beanions.common.dto.PostAndMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FreeBoardMapper {
    List<PostAndMemberDTO> freeList(SearchDTO params);

    List<PostAndMemberDTO> freeDetail(int id);

    void freeRegist(PostDTO postDTO);

    void freeModify(PostDTO id);

    void freeDelete(PostDTO postDTO);
}
