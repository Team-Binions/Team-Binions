package com.beanions.user.service;

import com.beanions.common.dao.user.FreeBoardMapper;
import com.beanions.common.dto.PostAndMemberDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreeBoardService {

    private final FreeBoardMapper freeBoardMapper;

    public FreeBoardService(FreeBoardMapper freeBoardMapper){
        this.freeBoardMapper = freeBoardMapper;
    }


    public List<PostAndMemberDTO> yesinAllList() {

        return freeBoardMapper.yesinAllList();

    }


    public List<PostAndMemberDTO> yesinDetail(String id) {

        return freeBoardMapper.yesinDetail(id);
    }
}
