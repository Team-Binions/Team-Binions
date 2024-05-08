package com.beanions.common.dao.user;

import com.beanions.common.dto.MainCategoryDTO;
import com.beanions.common.dto.PostAndMemberDTO;
import com.beanions.common.dto.PostAndMemberDTO;
import com.beanions.common.dto.SearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FreeBoardMapper {
    List<PostAndMemberDTO> yesinAllList(SearchDTO params);

    int count(SearchDTO params);


    List<PostAndMemberDTO> yesinDetail(String id);

    void postRegist(PostAndMemberDTO newPost);

    List<MainCategoryDTO> findMainCategory();
}
