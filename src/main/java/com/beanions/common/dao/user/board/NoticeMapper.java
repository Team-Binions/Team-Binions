package com.beanions.common.dao.user.board;

import com.beanions.common.dto.PostAndMemberDTO;
import com.beanions.common.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

    List<PostAndMemberDTO> allNoticeList();

    List<PostAndMemberDTO> noticeSelectOneDetail(int id);

}
