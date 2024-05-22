package com.beanions.main.service;
import com.beanions.common.dto.PostDTO;
import com.beanions.main.dao.MainMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MainService {
  private final MainMapper mainMapper;

  public MainService(MainMapper mainMapper) {this.mainMapper = mainMapper;}

  public List<PostDTO> selectFreeBoardByBride() {return mainMapper.selectFreeBoardByBride();}

  public List<PostDTO> selectFreeBoardByGroom() {return mainMapper.selectFreeBoardByGroom();}

  public List<PostDTO> selectReview() {return mainMapper.selectReview();}

  public List<PostDTO> selectMagazine() {return mainMapper.selectMagazine();}

  public List<PostDTO> selectNotice() {return mainMapper.selectNotice();}
}
