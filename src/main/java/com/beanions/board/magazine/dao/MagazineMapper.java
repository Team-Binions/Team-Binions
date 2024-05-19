package com.beanions.board.magazine.dao;

import com.beanions.board.common.dto.PostAndMemberDTO;
import com.beanions.common.dto.SearchDTO;
import com.beanions.common.paging.PagingResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MagazineMapper {
    @Update("UPDATE Post SET view_count = #{viewCount} WHERE Post_Code = #{id}")
    void updateViewCount(@Param("id") int id, @Param("viewCount") int viewCount);

    List<PostAndMemberDTO> magazineSelectOneDetail(int id);

    PagingResponse<PostAndMemberDTO> allMagazineList(final SearchDTO params);

}
