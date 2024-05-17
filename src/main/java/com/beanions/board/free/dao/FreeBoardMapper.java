package com.beanions.board.free.dao;

import com.beanions.board.common.dto.PostAndMemberDTO;
import com.beanions.common.dto.PostDTO;
import com.beanions.common.dto.SearchDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FreeBoardMapper {
    List<PostAndMemberDTO> freeList(String id, SearchDTO params);

    int count(SearchDTO params);

    List<PostAndMemberDTO> freeDetail(int id);

    void freeRegist(PostDTO postDTO);

    void freeModify(PostDTO id);

    void freeDelete(PostDTO postDTO);

    @Update("UPDATE Post SET view_count = #{viewCount} WHERE Post_Code = #{id}")
    void updateViewCount(@Param("id") int id, @Param("viewCount") int viewCount);
}
