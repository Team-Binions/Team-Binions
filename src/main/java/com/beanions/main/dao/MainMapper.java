package com.beanions.main.dao;

import com.beanions.common.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MainMapper {
  List<PostDTO> selectFreeBoardByBride();

  List<PostDTO> selectFreeBoardByGroom();

  List<PostDTO> selectReview();

  List<PostDTO> selectMagazine();

  List<PostDTO> selectNotice();
}
