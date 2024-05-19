package com.beanions.board.free.service;

import com.beanions.board.free.dao.FreeBoardMapper;
//import com.beanions.common.dto.Page;
import com.beanions.board.common.dto.PostAndMemberDTO;
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

    public PagingResponse<PostAndMemberDTO> freeList(String id, final SearchDTO params) {

        // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
        int count = freeBoardMapper.count(id, params);
        System.out.println("params = " + params);
        System.out.println("count = " + count);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }
        // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 계산된 페이지 정보 저장
        Pagination pagination = new Pagination(count, params);
        System.out.println("pagination = " + pagination);
        params.setPagination(pagination);
        System.out.println("확인용...");

        // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
        List<PostAndMemberDTO> list = freeBoardMapper.freeList(id, params);
        System.out.println("list = " + list);

        return new PagingResponse<>(list, pagination);
    }

    public List<PostAndMemberDTO> freeDetail(String id) {

        int code = Integer.parseInt(id);

        List<PostAndMemberDTO> postAndMemberDTOS = freeBoardMapper.freeDetail(code);

        if(postAndMemberDTOS != null && !postAndMemberDTOS.isEmpty()){
            PostAndMemberDTO post = postAndMemberDTOS.get(0);

            int updateViewCount = post.getViewCount() + 1;

            post.setViewCount(updateViewCount);

            freeBoardMapper.updateViewCount(code, updateViewCount);
        }
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
