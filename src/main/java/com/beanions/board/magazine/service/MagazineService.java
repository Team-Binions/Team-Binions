package com.beanions.board.magazine.service;

import com.beanions.board.common.dto.PostAndMemberDTO;
import com.beanions.board.magazine.dao.MagazineMapper;
import com.beanions.common.dto.SearchDTO;
import com.beanions.common.paging.PagingResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MagazineService {
    private final MagazineMapper magazineMapper;

    public MagazineService(MagazineMapper magazineMapper){
        this.magazineMapper = magazineMapper;
    }

    public PagingResponse<PostAndMemberDTO> allMagazineList(SearchDTO params) {


        return magazineMapper.allMagazineList(params);
    }


    public List<PostAndMemberDTO> selectMagazine(String id) {

        int code = Integer.parseInt(id);

        return magazineMapper.magazineSelectOneDetail(code);
    }
}

