package com.beanions.user.service.board;

import com.beanions.common.dao.user.board.NoticeMapper;
import com.beanions.common.dto.PostAndMemberDTO;
import com.beanions.common.dto.PostDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
