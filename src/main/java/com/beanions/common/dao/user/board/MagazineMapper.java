package com.beanions.common.dao.user.board;

import com.beanions.common.dto.PostAndMemberDTO;

import java.util.List;

public interface MagazineMapper {
    List<PostAndMemberDTO> magazineSelectOneDetail(int code);

    List<PostAndMemberDTO> allMagazineList();
}
