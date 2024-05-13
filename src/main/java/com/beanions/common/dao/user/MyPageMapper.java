package com.beanions.common.dao.user;

import com.beanions.common.dto.MyPageDTO;
import com.beanions.common.dto.SchedulesDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageMapper {
  List<MyPageDTO> selectAllMyPageData();

  List<MyPageDTO> selectMyPageMainData();

  List<MyPageDTO> selectMyPageReviewData();

  List<MyPageDTO> selectMyPageFreeData();

  List<MyPageDTO> selectMyPageCommentData();

  List<MyPageDTO> selectMyPageCommentPostCategory();

  void insertNewSchedule(SchedulesDTO newSchedule);

  List<MyPageDTO> selectMyPageScheduleInfo();

  List<MyPageDTO> selectScheduleDetail(int code);

  void modifySchedule(SchedulesDTO modifiedSchedule);

  void deleteSchedule(int code);
}
