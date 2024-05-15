package com.beanions.user.service;

import com.beanions.common.dao.user.MyPageMapper;
import com.beanions.common.dto.MyPageDTO;
import com.beanions.common.dto.SchedulesDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Transactional
  public void registNewSchedule(SchedulesDTO newSchedule) {
    myPageMapper.insertNewSchedule(newSchedule);
  }

  public List<MyPageDTO> selectMyPageScheduleInfo() {
    return myPageMapper.selectMyPageScheduleInfo();
  }

  public List<MyPageDTO> selectScheduleDetail(String id) {
    int code = Integer.parseInt(id);
    return myPageMapper.selectScheduleDetail(code);
  }

  @Transactional
  public void modifySchedule(SchedulesDTO modifiedSchedule) { myPageMapper.modifySchedule(modifiedSchedule);
  }

  @Transactional
  public void deleteSchedule(int code) {myPageMapper.deleteSchedule(code);}
}
