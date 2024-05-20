package com.beanions.mypage.dao;

import com.beanions.common.dto.FilesDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface UploadMapper {
  @Transactional
  List<FilesDTO> registWriting();

  List<FilesDTO> selectAllFiles();
}
