package com.beanions.mypage.dao;

import com.beanions.common.dto.FilesDTO;
import com.beanions.common.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface UploadMapper {
  @Transactional
  List<FilesDTO> registWriting();

  List<FilesDTO> selectAllFiles();

  int registPost(PostDTO post);

  PostDTO selectPost(int membercode);

  void insertFile(FilesDTO fileInfo);
}
