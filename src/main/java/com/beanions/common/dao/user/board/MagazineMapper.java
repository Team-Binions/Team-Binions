package com.beanions.common.dao.user.board;

import com.beanions.common.dto.PostAndMemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MagazineMapper {
    List<PostAndMemberDTO> magazineSelectOneDetail(int code);

    List<PostAndMemberDTO> allMagazineList();

}
