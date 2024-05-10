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

    @GetMapping( "/noticedetail")
    public String noticeDetail(@RequestParam("id") String id, Model model) {

        List<PostAndMemberDTO> notice = noticeService.selectNotice(id);

        model.addAttribute("notice", notice);

        return "user/board/noticeboarddetail";
    }
}