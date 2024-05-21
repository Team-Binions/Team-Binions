package com.beanions.board.review.controller;

import com.beanions.board.common.dto.PostAndMemberDTO;
import com.beanions.board.review.service.ReviewService;
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
@SessionAttributes
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService){

        this.reviewService = reviewService;

    }

    @GetMapping("/reviewList")
    public String reviewAllList(Model model, @ModelAttribute("params") final SearchDTO params) {

        PagingResponse<PostAndMemberDTO> PostAndMemberDTOList = reviewService.reviewAllList(params);

        model.addAttribute("PostAndMemberDTOList", PostAndMemberDTOList);

        return "user/board/reviewList";
    }

    @GetMapping("/reviewDetail")
    public String reviewDetail(@RequestParam("id") String id, Model model){

        List<PostAndMemberDTO> postAndMemberDTO = reviewService.reviewDetail(id);

        model.addAttribute("PostAndMemberDTO", postAndMemberDTO.get(0));

        return "user/board/reviewDetail";
    }

    @GetMapping("/reviewModify")
    public String modifyPost(@RequestParam("id") String id, Model model){

        List<PostAndMemberDTO> modify = reviewService.reviewDetail(id);

        model.addAttribute("modify", modify);

        return "user/board/reviewModify";
    }

    @PostMapping("/reviewModify")
    public String modifyPost(PostDTO postDTO, RedirectAttributes rttr){

        reviewService.modifyReview(postDTO);

        rttr.addFlashAttribute("successMessage", "수정 성공");

        return "redirect:/board/reviewList";
    }

    @PostMapping("/delete")
    public String deletePost(RedirectAttributes rttr, PostDTO postDTO){

        reviewService.deleteReview(postDTO);

        rttr.addFlashAttribute("successMessage", "게시글 삭제 성공");

        return "redirect:/board/reviewList";
    }

}
