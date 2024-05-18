package com.beanions.search.dao;

import com.beanions.board.common.dto.PostAndMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {
    List<PostAndMemberDTO> searchResult(String keyword);
}
