package com.beanions.user.service.board;

import com.beanions.common.dao.user.board.MagazineMapper;
import com.beanions.common.dto.PostAndMemberDTO;
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

