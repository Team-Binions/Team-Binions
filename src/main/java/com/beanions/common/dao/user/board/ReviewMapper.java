package com.beanions.common.dao.user.board;

import com.beanions.common.dto.*;
import com.beanions.common.dto.PostAndMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<PostAndMemberDTO> reviewAllList(SearchDTO params);

    List<PostAndMemberDTO> reviewDetail(int id);

    void reviewRegist(PostDTO postDTO);

    List<MainCategoryDTO> findMainCategory();

    void reviewModify(PostDTO id);

    void reviewDelete(PostDTO postDTO);
}
