package com.beanions.user.service;

import com.beanions.common.dao.user.MyPageMapper;
import com.beanions.common.dto.MyPageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPageService {

  private final MyPageMapper myPageMapper;

  public MyPageService(MyPageMapper myPageMapper) {
    this.myPageMapper = myPageMapper;
  }

  public List<MyPageDTO> selectAllMyPageData() {
    return myPageMapper.selectAllMyPageData();
  }

  public List<MyPageDTO> selectMyPageMainData() {
    return myPageMapper.selectMyPageMainData();
  }

  public List<MyPageDTO> selectMyPageReviewData() { return myPageMapper.selectMyPageReviewData();}

  public List<MyPageDTO> selectMyPageFreeData() { return myPageMapper.selectMyPageFreeData();}

  public List<MyPageDTO> selectMyPageCommentData() {return myPageMapper.selectMyPageCommentData();}

  public List<MyPageDTO> selectMyPageCommentPostCategory() {return myPageMapper.selectMyPageCommentPostCategory();}
}
