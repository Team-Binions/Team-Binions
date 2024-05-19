package com.beanions.board.notice.service;

import com.beanions.board.common.dto.PostAndMemberDTO;
import com.beanions.board.notice.dao.NoticeMapper;
import com.beanions.common.dto.SearchDTO;
import com.beanions.common.paging.Pagination;
import com.beanions.common.paging.PagingResponse;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class NoticeService {

    private final NoticeMapper noticeMapper;

    public NoticeService(NoticeMapper noticeMapper){
        this.noticeMapper = noticeMapper;
    }

    public PagingResponse<PostAndMemberDTO> allNoticeList(final SearchDTO params) {

        int count = noticeMapper.count(params);
        if(count <1){
            return new PagingResponse<>(Collections.emptyList(),null);
        }
        Pagination pagination = new Pagination(count, params);
        System.out.println("pagination = " + pagination);
        params.setPagination(pagination);
        System.out.println("확인용...");
        List<PostAndMemberDTO> list = noticeMapper.allNoticeList(params);
        System.out.println("list = " + list);

        return new PagingResponse<>(list, pagination);
    }

    public List<PostAndMemberDTO> selectNotice(String id) {

        int code = Integer.parseInt(id);
        return noticeMapper.noticeSelectOneDetail(code);
    }
}
