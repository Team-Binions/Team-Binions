package com.beanions.user.controller;

import com.beanions.common.dto.PostDTO;
import com.beanions.user.service.FreeBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class FreeBoardController {

    private final FreeBoardService freeBoardService;

    public FreeBoardController(FreeBoardService freeBoardService){
        this.freeBoardService = freeBoardService;
    }

    @GetMapping("/yesinList")
    public String yesinAllList(Model model) {

        List<PostDTO> postDTOList = freeBoardService.yesinAllList();

        for(PostDTO posts: postDTOList){
            System.out.println("posts = " + posts);
        }

        model.addAttribute("postDTOList", postDTOList);

        return "user/yesinList";

    }

    @GetMapping("/regist")
}
