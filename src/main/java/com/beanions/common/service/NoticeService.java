package com.beanions.common.service;

import com.beanions.common.dao.user.NoticeMapper;
import com.beanions.common.dto.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    private final NoticeMapper noticeMapper;

    public NoticeService(NoticeMapper noticeMapper){
        this.noticeMapper = noticeMapper;
    }

    public List<PostDTO> allNoticeList() {

        return noticeMapper.allNoticeList();
    }

    public List<PostDTO> selectNotice(String id) {

        return noticeMapper.noticeSelectOneDetail(id);
    }

}
