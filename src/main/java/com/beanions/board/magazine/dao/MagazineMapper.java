package com.beanions.board.magazine.dao;

import com.beanions.board.common.dto.PostAndMemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MagazineMapper {
    @Update("UPDATE Post SET view_count = #{viewCount} WHERE Post_Code = #{id}")
    void updateViewCount(@Param("id") int id, @Param("viewCount") int viewCount);

    List<PostAndMemberDTO> magazineSelectOneDetail(int id);

    List<PostAndMemberDTO> allMagazineList();

}
