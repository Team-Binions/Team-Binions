package com.beanions.board.magazine.service;

import com.beanions.board.common.dto.PostAndMemberDTO;
import com.beanions.board.magazine.dao.MagazineMapper;
import com.beanions.board.magazine.dto.MagazineDTO;
import com.beanions.board.magazine.controller.Pagination;
import com.beanions.board.magazine.controller.PagingResponse;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MagazineService {
    private final MagazineMapper magazineMapper;

    public MagazineService(MagazineMapper magazineMapper){
        this.magazineMapper = magazineMapper;
    }

    public PagingResponse<PostAndMemberDTO> allMagazineList(final MagazineDTO params) {
// 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
        int count = magazineMapper.count(params);
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
        List<PostAndMemberDTO> list = magazineMapper.allMagazineList(params);
        System.out.println("list = " + list);

        return new PagingResponse<>(list, pagination);

    }


    public PostAndMemberDTO selectMagazine(String id) {

        int code = Integer.parseInt(id);

        return magazineMapper.magazineSelectOneDetail(code);
    }
}

