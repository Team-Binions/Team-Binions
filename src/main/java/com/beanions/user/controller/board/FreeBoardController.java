package com.beanions.user.controller.board;

import com.beanions.common.dto.PostAndMemberDTO;
import com.beanions.common.dto.PostDTO;
import com.beanions.common.dto.SearchDTO;
import com.beanions.user.service.board.FreeBoardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequestMapping("/user/board")
public class FreeBoardController {


    private final FreeBoardService freeBoardService;

    public FreeBoardController(FreeBoardService freeBoardService){

        this.freeBoardService = freeBoardService;

    }

    @GetMapping("/yesinList")
    public String yesinList(@ModelAttribute("params") final SearchDTO params, Model model, String id) {

        List<PostAndMemberDTO> PostAndMemberDTOList = freeBoardService.freeList(params);

        model.addAttribute("PostAndMemberDTOList", PostAndMemberDTOList);

        return "/user/board/freeList";
    }

    @GetMapping("/yerangList")
    public String yerangList(@ModelAttribute("params") final SearchDTO params, Model model, String id) {

        List<PostAndMemberDTO> PostAndMemberDTOList = freeBoardService.freeList(params);

        model.addAttribute("PostAndMemberDTOList", PostAndMemberDTOList);

        return "/user/board/freeList";
    }

    @GetMapping("/yesinDetail")
    public String yesinDetail(@RequestParam("id") String id, Model model){

        List<PostAndMemberDTO> PostAndMemberDTO = freeBoardService.freeDetail(id);

        model.addAttribute("PostAndMemberDTO", PostAndMemberDTO);

        return "/user/board/freeDetail";
    }
    @GetMapping("/yerangDetail")
    public String yerangDetail(@RequestParam("id") String id, Model model){

        List<PostAndMemberDTO> PostAndMemberDTO = freeBoardService.freeDetail(id);

        model.addAttribute("PostAndMemberDTO", PostAndMemberDTO);

        return "/user/board/freeDetail";
    }

    @GetMapping("/freeRegist")
    public void postRegistPage(){}

    @PostMapping("/freeRegist")
    public String postRegist(PostDTO postDTO, RedirectAttributes rttr, HttpSession session){

//        Integer memberCode = (Integer) session.getAttribute("memberCode");
//
//        if (memberCode != null) {
//            postDTO.setMemberCode(memberCode);
//            freeBoardService.postRegist(postDTO);
//            rttr.addFlashAttribute("successMessage", "게시글을 등록하였습니다.");
//        } else {
//            rttr.addFlashAttribute("errorMessage", "로그인 후에 게시글을 작성할 수 있습니다.");
//        }

        freeBoardService.freeRegist(postDTO);

        System.out.println("newPost = " + postDTO);

        rttr.addFlashAttribute("successMessage", "게시글을 등록하였습니다.");

        // 예랑 게시판 생성 하면 주소 변경하기
        if (postDTO.getSubCategory().equals("예신")){
            return "redirect:/user/board/yesinList";
        } else {
            return "redirect:/user/board/yerangList";}
    }

    @GetMapping("/freemodify")
    public String modifyPost(@RequestParam("id") String id, Model model){

        List<PostAndMemberDTO> modify = freeBoardService.freeDetail(id);

        model.addAttribute("modify", modify);

        return "user/board/freemodify";
    }

    @PostMapping("/yesinmodify")
    public String yesinModify(PostDTO postDTO, RedirectAttributes rttr){

        freeBoardService.freeModify(postDTO);

        rttr.addFlashAttribute("successMessage", "수정 성공");

        // 예랑 게시판 생성 하면 주소 변경하기
        if (postDTO.getSubCategory().equals("예신")){
            return "redirect:/user/board/yesinList";
        } else {
            return "redirect:/user/board/yerangList";}
    }

    @PostMapping("/yerangmodify")
    public String yerangModify(PostDTO postDTO, RedirectAttributes rttr){

        freeBoardService.freeModify(postDTO);

        rttr.addFlashAttribute("successMessage", "수정 성공");

        // 예랑 게시판 생성 하면 주소 변경하기
        if (postDTO.getSubCategory().equals("예신")){
            return "redirect:/user/board/yesinList";
        } else {
            return "redirect:/user/board/yerangList";}
        }

    @PostMapping("/freedelete")
    public String deletePost(RedirectAttributes rttr, PostDTO postDTO){

        freeBoardService.deletePost(postDTO);

        rttr.addFlashAttribute("successMessage", "게시글 삭제 성공");

        // 예랑 게시판 생성 하면 주소 변경하기
        if (postDTO.getSubCategory().equals("예신")){
            return "redirect:/user/board/yesinList";
        } else {
            return "redirect:/user/board/yerangList";}
    }

}

