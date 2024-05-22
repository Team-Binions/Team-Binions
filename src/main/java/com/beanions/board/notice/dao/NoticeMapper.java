package com.beanions.board.notice.dao;

import com.beanions.board.common.dto.PostAndMemberDTO;
import com.beanions.common.dto.SearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

    List<PostAndMemberDTO> allNoticeList(SearchDTO params);

    PostAndMemberDTO noticeSelectOneDetail(int id);

    int count(SearchDTO params);
}
