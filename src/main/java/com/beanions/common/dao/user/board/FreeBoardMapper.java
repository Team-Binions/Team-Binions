package com.beanions.common.dao.user.board;

import com.beanions.common.dto.*;
import com.beanions.common.dto.PostAndMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FreeBoardMapper {
    List<PostAndMemberDTO> yesinAllList(SearchDTO params);

    int count(SearchDTO params);


    List<PostAndMemberDTO> yesinDetail(int id);

    void postRegist(PostDTO newPost);

    List<MainCategoryDTO> findMainCategory();

    void postModify(PostDTO id);

    void postDelete(PostDTO postDTO);
}
