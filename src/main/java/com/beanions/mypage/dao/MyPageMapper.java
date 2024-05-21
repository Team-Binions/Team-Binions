package com.beanions.mypage.dao;

import com.beanions.common.dto.MembersDTO;
import com.beanions.mypage.dto.MyPageDTO;
import com.beanions.mypage.dto.SchedulesDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageMapper {
  List<MyPageDTO> selectAllMyPageData();

  List<MyPageDTO> selectMyPageMainData(int memberCode);

  List<MyPageDTO> selectMyPageReviewData(int memberCode);

  List<MyPageDTO> selectMyPageFreeData(int memberCode);

  List<MyPageDTO> selectMyPageCommentData(int memberCode);

  List<MyPageDTO> selectMyPageCommentPostCategory(int memberCode);

  void insertNewSchedule(SchedulesDTO newSchedule);

  List<MyPageDTO> selectMyPageScheduleInfo(int memberCode);

  List<MyPageDTO> selectScheduleDetail(int memberCode, int code);

  void modifySchedule(SchedulesDTO modifiedSchedule);

  void deleteSchedule(int code);

  MyPageDTO selectMyPostInfo(int memberCode);

  void registWriting();

  int modifyMemberInfo(MembersDTO member);
}
