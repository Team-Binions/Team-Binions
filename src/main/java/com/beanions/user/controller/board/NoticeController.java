package com.beanions.user.controller.board;

import com.beanions.common.dto.PostAndMemberDTO;
import com.beanions.common.dto.PostDTO;
import com.beanions.user.service.board.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user/board")
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController (NoticeService noticeService){
        this.noticeService = noticeService;
    }

    @GetMapping("/notice")
    public String noticeList(Model model) {

        List<PostAndMemberDTO> noticeList = noticeService.allNoticeList();

        model.addAttribute("noticeList", noticeList);

        return "user/board/noticeboard";
    }

    @GetMapping( "/notice/detail")
    public String noticeDetail(@RequestParam("id") String id, Model model) {

        List<PostDTO> notice = noticeService.selectNotice(id);
        model.addAttribute("notice", notice);

        return "user/board/noticeboarddetail";
    }

    @GetMapping("/modify")
    public String modifyPost(@RequestParam("id") String id, Model model){


        List<PostDTO> modify = noticeService.selectNotice(id);
        model.addAttribute("modify", modify);

        System.out.println(modify);

        return "user/board/modify";
    }

    @PostMapping("/modify")
    public String modifyPost(PostDTO postDTO, RedirectAttributes rttr){

       noticeService.modifyPost(postDTO);
        rttr.addFlashAttribute("successMessage", "수정 성공..");

        return "redirect:/notice/board/board";
    }
}