package com.beanions.user.service.board;

import com.beanions.common.dao.user.board.FreeBoardMapper;
import com.beanions.common.dto.PostAndMemberDTO;
import com.beanions.common.dto.PostDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FreeBoardService {

    private final FreeBoardMapper freeBoardMapper;

    public FreeBoardService(FreeBoardMapper freeBoardMapper){
        this.freeBoardMapper = freeBoardMapper;
    }

    public List<PostAndMemberDTO> freeList(String id) {

        return freeBoardMapper.freeList(id);

    }

    public List<PostAndMemberDTO> freeDetail(String id) {

        int code = Integer.parseInt(id);

        List<PostAndMemberDTO> postAndMemberDTOS = freeBoardMapper.freeDetail(code);

        if(postAndMemberDTOS != null && !postAndMemberDTOS.isEmpty()){
            PostAndMemberDTO post = postAndMemberDTOS.get(0);

            int updateViewCount = post.getViewCount() + 1;

            post.setViewCount(updateViewCount);

            freeBoardMapper.updateViewCount(code, updateViewCount);
        }
        return freeBoardMapper.freeDetail(code);
    }


    @Transactional
    public void freeRegist(PostDTO postDTO) {

        freeBoardMapper.freeRegist(postDTO);
    }

    @Transactional
    public void freeModify(PostDTO postDTO) {
        freeBoardMapper.freeModify(postDTO);
    }


    public void deletePost(PostDTO postDTO) {
        freeBoardMapper.freeDelete(postDTO);
    }

}
