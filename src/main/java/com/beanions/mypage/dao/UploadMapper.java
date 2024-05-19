package com.beanions.mypage.dao;

import com.beanions.common.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UploadMapper {
  List<PostDTO> registWriting();
}
