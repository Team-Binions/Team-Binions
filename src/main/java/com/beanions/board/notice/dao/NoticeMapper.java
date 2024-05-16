package com.beanions.board.notice.dao;

import com.beanions.board.common.dto.PostAndMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

    List<PostAndMemberDTO> allNoticeList();

    List<PostAndMemberDTO> noticeSelectOneDetail(int id);

}
