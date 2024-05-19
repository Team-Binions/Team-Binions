package com.beanions.board.free.controller;

import com.beanions.board.common.dto.PostAndMemberDTO;
import com.beanions.board.free.service.FreeBoardService;
import com.beanions.common.dto.PostDTO;
import com.beanions.common.dto.SearchDTO;
import com.beanions.common.paging.PagingResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/board")
public class FreeBoardController {


    private final FreeBoardService freeBoardService;

    public FreeBoardController(FreeBoardService freeBoardService){

        this.freeBoardService = freeBoardService;

    }

    @GetMapping("/yesinList")
    public String yesinList(@RequestParam("id") String id , Model model, @ModelAttribute("params") final SearchDTO params) {

        PagingResponse<PostAndMemberDTO> PostAndMemberDTOList = freeBoardService.freeList(id, params);

        model.addAttribute("name", id);

        model.addAttribute("PostAndMemberDTOList", PostAndMemberDTOList);

        return "/user/board/freeList";
    }

    @GetMapping("/yerangList")
    public String yerangList(@RequestParam("id") String id , Model model, @ModelAttribute("params") final SearchDTO params) {

        PagingResponse<PostAndMemberDTO> PostAndMemberDTOList = freeBoardService.freeList(id, params);

        model.addAttribute("name", id);

        model.addAttribute("PostAndMemberDTOList", PostAndMemberDTOList);

        return "/user/board/freeList";
    }

    @GetMapping("/freeDetail")
    public String freeDetail(@RequestParam("id") String id, Model model){

        List<PostAndMemberDTO> PostAndMemberDTO = freeBoardService.freeDetail(id);

        model.addAttribute("PostAndMemberDTO", PostAndMemberDTO);

        return "/user/board/freeDetail";
    }

    @GetMapping("/freeRegist")
    public String postRegistPage(HttpSession session, RedirectAttributes rttr, Model model) {

        Integer memberCode = (Integer) session.getAttribute("memberCode");

        model.addAttribute("memberCode", memberCode);

        if (memberCode != null) {
            // 회원 아이디를 글쓰기 페이지로 리다이렉트하면서 전달
            return "/user/board/freeRegist";
        } else {
            // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
            rttr.addFlashAttribute("errorMessage", "로그인 후에 게시글을 작성할 수 있습니다.");
            return "redirect:/login";
        }
    }

    @PostMapping("/freeRegist")
    public String postRegist(PostDTO postDTO, RedirectAttributes rttr){

        freeBoardService.freeRegist(postDTO);

        rttr.addAttribute("id", postDTO.getSubCategory());
        rttr.addFlashAttribute("successMessage", "게시글을 등록하였습니다.");

        if (postDTO.getSubCategory().equals("예신")){
            return "redirect:/board/yesinList";

        } else if (postDTO.getSubCategory().equals("예랑")){
            return "redirect:/board/yerangList";

        } else { return "redirect:/board/reviewList"; }


    }

    @GetMapping("/yesinmodify")
    public String yesinModify(@RequestParam("id") String id, Model model){

        List<PostAndMemberDTO> modify = freeBoardService.freeDetail(id);

        model.addAttribute("modify", modify);

        return "user/board/freeModify";
    }

    @GetMapping("/yerangmodify")
    public String yerangModify(@RequestParam("id") String id, Model model){

        List<PostAndMemberDTO> modify = freeBoardService.freeDetail(id);

        model.addAttribute("modify", modify);

        return "user/board/freeModify";
    }

    @PostMapping("/freeModify")
    public String freeModify(PostDTO postDTO, RedirectAttributes rttr){

        freeBoardService.freeModify(postDTO);

        rttr.addAttribute("id", postDTO.getSubCategory());

        rttr.addFlashAttribute("successMessage", "수정 성공");
        // 예랑 게시판 생성 하면 주소 변경하기
        if (postDTO.getSubCategory().equals("예신")){
            return "redirect:/board/yesinList";
        } else {
            return "redirect:/board/yerangList";
        }
        }

    @PostMapping("/freedelete")
    public String deletePost(RedirectAttributes rttr, PostDTO postDTO){

        freeBoardService.deletePost(postDTO);

        rttr.addAttribute("id", postDTO.getSubCategory());
        rttr.addFlashAttribute("successMessage", "게시글 삭제 성공");

        // 예랑 게시판 생성 하면 주소 변경하기
        if (postDTO.getSubCategory().equals("예신")){
            return "redirect:/board/yesinList";
        } else {
            return "redirect:/board/yerangList";}
    }

    //240517 by NJH - 댓글 작업용 도메인
    @GetMapping("/freedetail")
    public String freeDetailComment(@RequestParam("id") String id, Model model){

        List<PostAndMemberDTO> PostAndMemberDTO = freeBoardService.freeDetail(id);

        model.addAttribute("PostAndMemberDTO", PostAndMemberDTO);

        return "/user/board/freeDetail";
    }
}

