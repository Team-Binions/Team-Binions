package com.beanions.search.dao;

import com.beanions.board.common.dto.PostAndMemberDTO;
import com.beanions.common.dto.SearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {
    List<PostAndMemberDTO> searchResult(SearchDTO params);

    int count(SearchDTO params);
}
