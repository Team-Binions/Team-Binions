package com.beanions.search.service;

import com.beanions.board.common.dto.PostAndMemberDTO;
import com.beanions.common.dto.SearchDTO;
import com.beanions.common.paging.Pagination;
import com.beanions.common.paging.PagingResponse;
import com.beanions.search.dao.SearchMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SearchService {

    private final SearchMapper searchMapper;

    public SearchService(SearchMapper searchMapper) {
        this.searchMapper = searchMapper;
    }

    public List<PostAndMemberDTO> searchResult(final SearchDTO params) {

        return searchMapper.searchResult(params);
    }

    public int count(SearchDTO params) {

        return searchMapper.count(params);
    }
}
