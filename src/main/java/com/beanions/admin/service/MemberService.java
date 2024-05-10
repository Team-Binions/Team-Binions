package com.beanions.admin.service;

import com.beanions.common.dao.admin.AdminMapper;
import com.beanions.common.dto.MembersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {

    private final AdminMapper adminMapper;

    public MemberService(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    public List<MembersDTO> membersAllList() {

        return adminMapper.membersAllList();
    }

    @Transactional
    public void modifyOneMember(MembersDTO modify) {

        adminMapper.modifyOneMember(modify);
    }
}
