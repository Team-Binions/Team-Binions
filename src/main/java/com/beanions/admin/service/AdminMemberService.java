package com.beanions.admin.service;

import com.beanions.admin.dao.AdminMapper;
import com.beanions.common.dto.MembersDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminMemberService {

    private final AdminMapper adminMapper;

    public AdminMemberService(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    public List<MembersDTO> membersAllList() {

        return adminMapper.membersAllList();
    }

    public List<MembersDTO> selectMembers(int code) {

        return adminMapper.selectMembers(code);
    }

    @Transactional
    public void memberModify(MembersDTO modify) {

        System.out.println("memberModify service 매소드 호출...");
        System.out.println("modify = " + modify);
        adminMapper.memberModify(modify);
    }

    @Transactional
    public void memberDelete(int memberCode) {

        adminMapper.memberDelete(memberCode);
    }

//    public List<AdminPostDTO> oneMemberPost(int codes) {
//
//        return adminMapper.oneMemberPost(codes);
//    }
}
