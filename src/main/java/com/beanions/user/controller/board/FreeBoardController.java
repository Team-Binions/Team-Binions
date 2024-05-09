package com.beanions.user.controller.board;

import com.beanions.common.dto.MainCategoryDTO;
import com.beanions.common.dto.PostAndMemberDTO;
import com.beanions.common.dto.PostDTO;
import com.beanions.common.dto.SearchDTO;
import com.beanions.user.service.board.FreeBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
@RequestMapping("/user/board")
public class FreeBoardController {

    private final FreeBoardService freeBoardService;

    public FreeBoardController(FreeBoardService freeBoardService){
        this.freeBoardService = freeBoardService;
    }

    @GetMapping("/yesinList")
    public String yesinAllList(@ModelAttribute("params") final SearchDTO params, Model model, String id) {

        List<PostAndMemberDTO> PostAndMemberDTOList = freeBoardService.yesinAllList(params);

        for(PostAndMemberDTO posts: PostAndMemberDTOList){
            System.out.println("posts = " + posts);
        }

        System.out.println("id = " + id);

        model.addAttribute("PostAndMemberDTOList", PostAndMemberDTOList);


        return "user/board/yesinList";

    }

    @GetMapping("/yesinDetail")
    public String yesinDetail(@RequestParam("id") String id,Model model){

        List<PostAndMemberDTO> PostAndMemberDTO = freeBoardService.yesinDetail(id);

        model.addAttribute("PostAndMemberDTO", PostAndMemberDTO);

        return "user/board/yesinDetail";
    }

    @GetMapping("/postRegist")
    public void postRegistPage(){}


    @PostMapping("/postRegist")

    public String postRegist(PostDTO postDTO, RedirectAttributes rttr){

        freeBoardService.postRegist(postDTO);

        System.out.println("newPost = " + postDTO);

        rttr.addFlashAttribute("successMessage", "게시글을 등록하였습니다.");

        if (postDTO.getSubCategory().equals("예신")){
            return "redirect:/user/board/yesinList";
        } else {
            return "redirect:/user/board/yesinList";
        }
    }

    @GetMapping("/modify")
    public String modifyPost(@RequestParam("id") String id, Model model){

        List<PostAndMemberDTO> modify = freeBoardService.yesinDetail(id);

        model.addAttribute("modify", modify);

        return "user/board/freeboardmodify";
    }

    @PostMapping("/modify")
    public String modifyPost(PostDTO postDTO, RedirectAttributes rttr){

        freeBoardService.modifyPost(postDTO);

        rttr.addFlashAttribute("successMessage", "수정 성공");

        System.out.println("postDTO = " + postDTO);

        // 예랑 게시판 생성 하면 주소 변경하기
        if (postDTO.getSubCategory().equals("예신")){
            return "redirect:/user/board/yesinList";
        }else {
            return "redirect:/user/board/yesinList";
        }
    }

    @PostMapping("/delete")
    public String deletePost(RedirectAttributes rttr, PostDTO postDTO){

        freeBoardService.deletePost(postDTO);


        System.out.println("postDTO = " + postDTO);
        rttr.addFlashAttribute("successMessage", "게시글 삭제 성공");

        // 예랑 게시판 생성 하면 주소 변경하기
        if (postDTO.getSubCategory().equals("예신")){
            return "redirect:/user/board/yesinList";
        }else {
            return "redirect:/user/board/yesinList";
        }

    }
}
