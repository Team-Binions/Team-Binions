package com.beanions.board.notice.service;

import com.beanions.board.common.dto.PostAndMemberDTO;
import com.beanions.board.notice.dao.NoticeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    private final NoticeMapper noticeMapper;

    public NoticeService(NoticeMapper noticeMapper){
        this.noticeMapper = noticeMapper;
    }

    public List<PostAndMemberDTO> allNoticeList() {

        return noticeMapper.allNoticeList();
    }

    public List<PostAndMemberDTO> selectNotice(String id) {

        int code = Integer.parseInt(id);
        return noticeMapper.noticeSelectOneDetail(code);
    }
}
