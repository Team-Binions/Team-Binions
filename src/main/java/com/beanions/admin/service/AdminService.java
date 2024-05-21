package com.beanions.admin.service;

import com.beanions.admin.dao.AdminMapper;
import com.beanions.admin.dto.AdminMainDTO;
import com.beanions.admin.dto.AdminPostDTO;
import com.beanions.common.dto.PostDTO;
import com.beanions.common.dto.SearchDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {

    private final AdminMapper adminMapper;
    public AdminService(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    public List<AdminPostDTO> selectAllPost(final SearchDTO params) {

        return adminMapper.selectAllPost(params);
    }

    public AdminPostDTO selectPost(int code) {

        return adminMapper.selectPost(code);
    }

    @Transactional
    public void postUpdate(PostDTO post) {

        System.out.println("postUpdate service 매소드 호출...");
        System.out.println("post = " + post);
        adminMapper.postUpdate(post);
    }

    @Transactional
    public void postReview(PostDTO post) {

        adminMapper.postReview(post);
    }

//    public void postDelete(Long id) {
//    }

    @Transactional
    public void postDelete(int postCode) {

        adminMapper.postDelete(postCode);
    }

    public List<AdminPostDTO> selectAllNotice(final SearchDTO params) {

        return adminMapper.selectAllNotice(params);
    }

    @Transactional
    public void noticeRegist(PostDTO newNotice) {

        adminMapper.noticeRegist(newNotice);
    }

    @Transactional
    public void noticeUpdate(PostDTO post) {

        adminMapper.noticeUpdate(post);
    }

    public List<AdminPostDTO> selectAllMagazine(final SearchDTO params) {

        return adminMapper.selectAllMagazine(params);
    }

    @Transactional
    public void magazineRegist(PostDTO newMagazine) {

        adminMapper.magazineRegist(newMagazine);
    }

    public List<PostDTO> selectCurrentBoard() {

        return adminMapper.selectCurrentBoard();
    }

    public List<PostDTO> selectCurrentMagazine() {

        return adminMapper.selectCurrentMagazine();
    }

    public List<AdminMainDTO> selectAdminMainData() {

        return adminMapper.selectAdminMainData();
    }

    public int count(SearchDTO params) {

        return adminMapper.count(params);
    }

    public int countNotice(SearchDTO params) {

        return adminMapper.countNotice(params);
    }

    public int countMagazine(SearchDTO params) {

        return adminMapper.countMagazine(params);
    }
}
