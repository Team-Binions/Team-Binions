package com.beanions.admin.dao;

import com.beanions.admin.dto.AdminMainDTO;
import com.beanions.admin.dto.AdminMemberPostDTO;
import com.beanions.admin.dto.AdminPostDTO;
import com.beanions.common.dto.MembersDTO;
import com.beanions.common.dto.PostDTO;
import com.beanions.common.dto.SearchDTO;
import com.beanions.mypage.dto.MyPageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AdminMapper {
    List<MembersDTO> membersAllList(SearchDTO params);

    MembersDTO selectMembers(int code);

    void memberModify(MembersDTO modify);

    void memberDelete(int memberCode);

    List<PostDTO> selectMemberPost(int codes);

    AdminMemberPostDTO selectAdminReviewData(int codes);

    List<AdminPostDTO> selectAllPost(SearchDTO params);


    AdminPostDTO selectPost(int code);

    void postUpdate(PostDTO post);

    void postReview(PostDTO post);

    void postDelete(int postCode);

    List<AdminPostDTO> selectAllNotice(SearchDTO params);

    void noticeRegist(PostDTO newNotice);

    void noticeUpdate(PostDTO post);

    List<AdminPostDTO> selectAllMagazine(SearchDTO params);

    void magazineRegist(PostDTO newMagazine);
  
    List<PostDTO> selectCurrentBoard();

    List<PostDTO> selectCurrentMagazine();

    List<AdminMainDTO> selectAdminMainData();

    int count(SearchDTO params);

    int countNotice(SearchDTO params);

    int countMagazine(SearchDTO params);
}
