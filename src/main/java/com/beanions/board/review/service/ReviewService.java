package com.beanions.board.review.service;

import com.beanions.board.common.dto.PostAndMemberDTO;
import com.beanions.board.review.dao.ReviewMapper;
import com.beanions.common.dto.PostDTO;
import com.beanions.common.dto.SearchDTO;
import com.beanions.common.paging.Pagination;
import com.beanions.common.paging.PagingResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewMapper reviewMapper){
        this.reviewMapper = reviewMapper;
    }


    public PagingResponse<PostAndMemberDTO> reviewAllList(final SearchDTO params) {
// 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
        int count = reviewMapper.count(params);
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
        List<PostAndMemberDTO> list = reviewMapper.reviewAllList(params);
        System.out.println("list = " + list);

        return new PagingResponse<>(list, pagination);

    }


    public List<PostAndMemberDTO> reviewDetail(String id) {

        int code = Integer.parseInt(id);

        List<PostAndMemberDTO> postAndMemberDTOS = reviewMapper.reviewDetail(code);

        if(postAndMemberDTOS != null && !postAndMemberDTOS.isEmpty()){
            PostAndMemberDTO post = postAndMemberDTOS.get(0);

            int updateViewCount = post.getViewCount() + 1;

            post.setViewCount(updateViewCount);

            reviewMapper.updateViewCount(code, updateViewCount);
        }

        return reviewMapper.reviewDetail(code);
    }

    @Transactional
    public void reviewRegist(PostDTO postDTO) {

        reviewMapper.reviewRegist(postDTO);
    }

    @Transactional
    public void modifyReview(PostDTO postDTO) {
        reviewMapper.reviewModify(postDTO);
    }

    public void deleteReview(PostDTO postDTO) {
        reviewMapper.reviewDelete(postDTO);
    }
}
