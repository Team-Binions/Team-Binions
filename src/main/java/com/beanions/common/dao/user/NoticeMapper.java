package com.beanions.common.dao.user;

import com.beanions.common.dto.PostAndMemberDTO;
import com.beanions.common.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

    List<PostAndMemberDTO> allNoticeList();

    List<PostDTO> noticeSelectOneDetail(String id);

//    List<PostDTO> modifyPost(PostDTO modifyNewMenu);
    List<PostDTO> modifyPost(String id);

    //    PostDTO prevNotice(int id);
}
