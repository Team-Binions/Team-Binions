package com.beanions.search.service;

import com.beanions.board.common.dto.PostAndMemberDTO;
import com.beanions.search.dao.SearchMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final SearchMapper searchMapper;

    public SearchService(SearchMapper searchMapper) {
        this.searchMapper = searchMapper;
    }

    public List<PostAndMemberDTO> searchResult(String keyword) {

        return searchMapper.searchResult(keyword);
    }
}
