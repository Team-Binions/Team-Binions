package com.beanions.user.service.board;

import com.beanions.common.dao.user.board.ReviewMapper;
import com.beanions.common.dto.MainCategoryDTO;
import com.beanions.common.dto.PostAndMemberDTO;
import com.beanions.common.dto.PostDTO;
import com.beanions.common.dto.SearchDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewMapper reviewMapper){
        this.reviewMapper = reviewMapper;
    }


    public List<PostAndMemberDTO> reviewAllList() {

        return reviewMapper.reviewAllList();

    }

//    public PagingResponse<PostAndMemberDTO> findAllReview(final SearchDTO params) {
//
//        // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
//        int count = reviewMapper.count(params);
//        if (count < 1) {
//            return new PagingResponse<>(Collections.emptyList(), null);
//        }
//
//        // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 계산된 페이지 정보 저장
//        Pagination pagination = new Pagination(count, params);
//        params.setPagination(pagination);
//
//        // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
//        List<PostAndMemberDTO> list = reviewMapper.reviewAllList(params);
//        return new PagingResponse<>(list, pagination);
//    }

    public List<PostAndMemberDTO> reviewDetail(String id) {

        int code = Integer.parseInt(id);

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
