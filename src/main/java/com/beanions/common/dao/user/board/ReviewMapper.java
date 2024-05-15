package com.beanions.common.dao.user.board;

import com.beanions.common.dto.*;
import com.beanions.common.dto.PostAndMemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<PostAndMemberDTO> reviewAllList();

    List<PostAndMemberDTO> reviewDetail(int id);

    void reviewRegist(PostDTO postDTO);

    List<MainCategoryDTO> findMainCategory();

    void reviewModify(PostDTO id);

    void reviewDelete(PostDTO postDTO);

    @Update("UPDATE Post SET view_count = #{viewCount} WHERE Post_Code = #{id}")
    void updateViewCount(@Param("id") int id, @Param("viewCount") int viewCount);
}
