package com.beanions.admin.service;

import com.beanions.common.dao.admin.AdminMapper;
import com.beanions.common.dto.AdminPostDTO;
import com.beanions.common.dto.PostDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void postUpdate(PostDTO post) {

        System.out.println("postUpdate service 매소드 호출...");
        System.out.println("post = " + post);
        adminMapper.postUpdate(post);
    }

//    public void postDelete(Long id) {
//    }

    @Transactional
    public void postDelete(int postCode) {

        adminMapper.postDelete(postCode);
    }

    public List<AdminPostDTO> selectAllNotice() {

        return adminMapper.selectAllNotice();
    }

    public void noticeRegist(PostDTO newNotice) {

        adminMapper.noticeRegist(newNotice);
    }

    public void noticeUpdate(PostDTO post) {

        adminMapper.noticeUpdate(post);
    }

    public List<AdminPostDTO> selectAllMagazine() {

        return adminMapper.selectAllMagazine();
    }

    public void magazineRegist(PostDTO newMagazine) {

        adminMapper.magazineRegist(newMagazine);
    }
}
