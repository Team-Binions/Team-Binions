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
    public String yesinAllList(@ModelAttribute("params") final SearchDTO params, Model model) {

        List<PostAndMemberDTO> PostAndMemberDTOList = freeBoardService.yesinAllList(params);

        for(PostAndMemberDTO posts: PostAndMemberDTOList){
            System.out.println("posts = " + posts);
        }

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


    @GetMapping(value = "category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<MainCategoryDTO> findMainCategoryList(){
        return freeBoardService.findMainCategory();
    }

    @PostMapping("/postRegist")

    public String postRegist(PostDTO newPost, RedirectAttributes rttr){

        freeBoardService.postRegist(newPost);

        rttr.addFlashAttribute("successMessage", "게시글을 등록하였습니다.");

        return "redirect:/user/board/yesinList";
    }

}
