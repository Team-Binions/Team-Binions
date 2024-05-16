package com.beanions.board.magazine.dao;

import com.beanions.board.common.dto.PostAndMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MagazineMapper {
    List<PostAndMemberDTO> magazineSelectOneDetail(int code);

    List<PostAndMemberDTO> allMagazineList();

}
