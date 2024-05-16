package com.beanions.board.magazine.service;

import com.beanions.board.common.dto.PostAndMemberDTO;
import com.beanions.board.magazine.dao.MagazineMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MagazineService {
    private final MagazineMapper magazineMapper;

    public MagazineService(MagazineMapper magazineMapper){
        this.magazineMapper = magazineMapper;
    }

    public List<PostAndMemberDTO> allMagazineList() {


        return magazineMapper.allMagazineList();
    }


    public List<PostAndMemberDTO> selectMagazine(String id) {

        int code = Integer.parseInt(id);

        return magazineMapper.magazineSelectOneDetail(code);
    }
}

