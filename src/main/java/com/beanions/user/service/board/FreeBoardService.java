package com.beanions.user.service.board;

import com.beanions.common.dao.user.board.FreeBoardMapper;
import com.beanions.common.dto.MainCategoryDTO;
import com.beanions.common.dto.PostAndMemberDTO;
import com.beanions.common.dto.PostDTO;
import com.beanions.common.dto.SearchDTO;
import com.beanions.common.paging.Pagination;
import com.beanions.common.paging.PagingResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class FreeBoardService {

    private final FreeBoardMapper freeBoardMapper;

    public FreeBoardService(FreeBoardMapper freeBoardMapper){
        this.freeBoardMapper = freeBoardMapper;
    }

    public List<PostAndMemberDTO> freeList(final SearchDTO params) {

        return freeBoardMapper.freeList(params);

    }

    public List<PostAndMemberDTO> freeDetail(String id) {

        int code = Integer.parseInt(id);

        return freeBoardMapper.freeDetail(code);
    }

    @Transactional
    public void freeRegist(PostDTO postDTO) {

        freeBoardMapper.freeRegist(postDTO);
    }

    @Transactional
    public void freeModify(PostDTO postDTO) {
        freeBoardMapper.freeModify(postDTO);
    }


    public void deletePost(PostDTO postDTO) {
        freeBoardMapper.freeDelete(postDTO);
    }

}
