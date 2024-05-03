package com.beanions.admin.service;

import com.beanions.common.dao.admin.AdminMapper;
import com.beanions.common.dto.AdminPostDTO;
import com.beanions.common.dto.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminMapper adminMapper;
    public AdminService(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    public List<AdminPostDTO> selectAllPost() {

        return adminMapper.selectAllPost();
    }

    public List<AdminPostDTO> selectPost(int code) {

        return adminMapper.selectPost(code);
    }
}
