package com.beanions.common.dao.admin;

import com.beanions.common.dto.AdminPostDTO;
import com.beanions.common.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    List<AdminPostDTO> selectAllPost();

    List<AdminPostDTO> selectPost(int code);
}
