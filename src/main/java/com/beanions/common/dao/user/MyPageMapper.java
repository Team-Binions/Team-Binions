package com.beanions.common.dao.user;

import com.beanions.common.dto.MyPageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageMapper {
  List<MyPageDTO> selectAllMyPageData();

  List<MyPageDTO> selectMyPageMainData();
}
