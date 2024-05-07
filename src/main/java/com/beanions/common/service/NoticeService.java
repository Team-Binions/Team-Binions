package com.beanions.common.service;

import com.beanions.common.dao.user.NoticeMapper;
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


    public List<PostDTO> selectNotice(String id) {

        return noticeMapper.noticeSelectOneDetail(id);
    }


//    @Transactional
//    public List<PostDTO> modifyPost(PostDTO modifyNewMenu) {
//
//        return noticeMapper.modifyPost(modifyNewMenu);
//    }
    @Transactional
    public List<PostDTO> modifyPost(String id) {

        return noticeMapper.modifyPost(id);
    }


//    public PostDTO getPrevNotice(int id) {
//
//        return noticeMapper.prevNotice(id);
//    }
}
