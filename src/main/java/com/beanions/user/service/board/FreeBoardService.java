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


    public List<PostAndMemberDTO> yesinAllList(final SearchDTO params) {

        return freeBoardMapper.yesinAllList(params);

    }

    public PagingResponse<PostAndMemberDTO> findAllPost(final SearchDTO params) {

        // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
        int count = freeBoardMapper.count(params);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 계산된 페이지 정보 저장
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
        List<PostAndMemberDTO> list = freeBoardMapper.yesinAllList(params);
        return new PagingResponse<>(list, pagination);
    }

    public List<PostAndMemberDTO> yesinDetail(String id) {

        int code = Integer.parseInt(id);

        return freeBoardMapper.yesinDetail(code);
    }

    @Transactional
    public void postRegist(PostDTO postDTO) {

        freeBoardMapper.postRegist(postDTO);
    }

    public List<MainCategoryDTO> findMainCategory() {
        return freeBoardMapper.findMainCategory();
    }

    @Transactional
    public void modifyPost(PostDTO postDTO) {
        freeBoardMapper.postModify(postDTO);
    }

    public void deletePost(PostDTO postDTO) {
        freeBoardMapper.postDelete(postDTO);
    }
}
