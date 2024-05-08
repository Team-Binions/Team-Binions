package com.beanions.user.controller;

import com.beanions.common.dto.MainCategoryDTO;
import com.beanions.common.dto.MembersDTO;
import com.beanions.common.dto.PostAndMemberDTO;
import com.beanions.common.dto.SearchDTO;
import com.beanions.user.service.FreeBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.awt.print.Pageable;
import java.util.List;

@Controller
@RequestMapping("/user")
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


        return "user/yesinList";

    }

    @GetMapping("/yesinDetail")
    public String yesinDetail(@RequestParam("id") String id,Model model){
        List<PostAndMemberDTO> PostAndMemberDTO = freeBoardService.yesinDetail(id);

        model.addAttribute("PostAndMemberDTO", PostAndMemberDTO);

        return "user/yesinDetail";
    }

    @GetMapping("/postRegist")
    public void postRegistPage(){}


    @GetMapping(value = "category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<MainCategoryDTO> findMainCategoryList(){
        return freeBoardService.findMainCategory();
    }
    @PostMapping("/postRegist")

    public String postRegist(PostAndMemberDTO newPost, RedirectAttributes rttr){

        freeBoardService.postRegist(newPost);

        rttr.addFlashAttribute("successMessage", "신규등록에 성공하였습니다.");

        return "redirect:/user/yesin";
    }

}
