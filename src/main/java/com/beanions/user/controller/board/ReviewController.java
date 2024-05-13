package com.beanions.user.controller.board;

import com.beanions.common.dto.PostAndMemberDTO;
import com.beanions.common.dto.PostDTO;
import com.beanions.common.dto.SearchDTO;
import com.beanions.user.service.board.ReviewService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
@RequestMapping("/user/board")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService){

        this.reviewService = reviewService;

    }

    @GetMapping("/reviewList")
    public String reviewAllList(Model model) {

        List<PostAndMemberDTO> PostAndMemberDTOList = reviewService.reviewAllList();

        model.addAttribute("PostAndMemberDTOList", PostAndMemberDTOList);

        return "user/board/reviewList";
    }

    @GetMapping("/reviewDetail")
    public String reviewDetail(@RequestParam("id") String id, Model model){

        List<PostAndMemberDTO> PostAndMemberDTO = reviewService.reviewDetail(id);

        model.addAttribute("PostAndMemberDTO", PostAndMemberDTO);

        return "user/board/reviewDetail";
    }


    @GetMapping("/reviewRegist")
    public void reviewRegistPage(){}

    @PostMapping("/reviewRegist")

    public String reviewRegist(PostDTO postDTO, RedirectAttributes rttr, HttpSession session){

//        Integer memberCode = (Integer) session.getAttribute("memberCode");
//
//        if (memberCode != null) {
//            postDTO.setMemberCode(memberCode);
//            reviewService.reviewRegist(postDTO);
//            rttr.addFlashAttribute("successMessage", "게시글을 등록하였습니다.");
//        } else {
//            rttr.addFlashAttribute("errorMessage", "로그인 후에 게시글을 작성할 수 있습니다.");
//        }

        reviewService.reviewRegist(postDTO);

        rttr.addFlashAttribute("successMessage", "게시글을 등록하였습니다.");

            return "redirect:/user/board/reviewList";

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

        return "redirect:/user/board/reviewList";
    }

    @PostMapping("/delete")
    public String deletePost(RedirectAttributes rttr, PostDTO postDTO){

        reviewService.deleteReview(postDTO);

        rttr.addFlashAttribute("successMessage", "게시글 삭제 성공");

        return "redirect:/user/board/reviewList";
    }

}
