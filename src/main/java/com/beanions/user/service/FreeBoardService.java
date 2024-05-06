package com.beanions.user.service;

import com.beanions.common.dao.user.FreeBoardMapper;
import com.beanions.common.dto.PostAndMemberDTO;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class FreeBoardService {

    private final FreeBoardMapper freeBoardMapper;

    public FreeBoardService(FreeBoardMapper freeBoardMapper){
        this.freeBoardMapper = freeBoardMapper;
    }


    public List<PostAndMemberDTO> yesinAllList(Pageable pageable) {

        int page = pageable.getNumberOfPages() - 1;
        int pageLimit = 20;

        
        return freeBoardMapper.yesinAllList();

    }


    public List<PostAndMemberDTO> yesinDetail(String id) {

        return freeBoardMapper.yesinDetail(id);
    }
}
