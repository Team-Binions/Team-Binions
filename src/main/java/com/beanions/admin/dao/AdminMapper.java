package com.beanions.admin.dao;

import com.beanions.admin.dto.AdminMainDTO;
import com.beanions.admin.dto.AdminPostDTO;
import com.beanions.common.dto.MembersDTO;
import com.beanions.common.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AdminMapper {
    List<MembersDTO> membersAllList();

    MembersDTO selectMembers(int code);

    void memberModify(MembersDTO modify);

    void memberDelete(int memberCode);


    List<PostDTO> selectMemberPost(int codes);

    List<AdminPostDTO> selectAllPost();


    List<AdminPostDTO> selectPost(int code);

    void postUpdate(PostDTO post);

    void postReview(PostDTO post);

    void postDelete(int postCode);

    List<AdminPostDTO> selectAllNotice();

    void noticeRegist(PostDTO newNotice);

    void noticeUpdate(PostDTO post);

    List<AdminPostDTO> selectAllMagazine();

    void magazineRegist(PostDTO newMagazine);
  
    List<PostDTO> selectCurrentBoard();

    List<PostDTO> selectCurrentMagazine();

    List<AdminMainDTO> selectAdminMainData();

}
