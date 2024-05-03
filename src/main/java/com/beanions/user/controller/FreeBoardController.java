package com.beanions.user.controller;

import com.beanions.common.dto.MembersDTO;
import com.beanions.common.dto.PostAndMemberDTO;
import com.beanions.user.service.FreeBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String yesinAllList(Model model, @PageableDefault(size = PageConfig.PAGE_PER_COUNT, sort = PageConfig.SORT_STANDARD, direction = Sort.Direction.DESC) Pageable pageable) {

        List<PostAndMemberDTO> PostAndMemberDTOList = freeBoardService.yesinAllList();

        for(PostAndMemberDTO posts: PostAndMemberDTOList){
            System.out.println("posts = " + posts);
        }

        model.addAttribute("PostAndMemberDTOList", PostAndMemberDTOList);
        model.addAttribute("maxPage",10);



        return "user/yesinList";

    }

    @GetMapping("/yesinDetail")
    public String yesinDetail(@RequestParam("id") String id,Model model){
        List<PostAndMemberDTO> PostAndMemberDTO = freeBoardService.yesinDetail(id);

        model.addAttribute("PostAndMemberDTO", PostAndMemberDTO);

        return "user/yesinDetail";
    }

//    @GetMapping("/regist")
}
