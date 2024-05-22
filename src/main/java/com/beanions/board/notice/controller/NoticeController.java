package com.beanions.board.notice.controller;

import com.beanions.board.common.dto.PostAndMemberDTO;
import com.beanions.board.notice.service.NoticeService;
import com.beanions.common.dto.SearchDTO;
import com.beanions.common.paging.PagingResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController (NoticeService noticeService){
        this.noticeService = noticeService;
    }

    @GetMapping("/notice")
    public String noticeList(Model model, @ModelAttribute("params") final SearchDTO params) {

        PagingResponse<PostAndMemberDTO> noticeList = noticeService.allNoticeList(params);

        model.addAttribute("noticeList", noticeList);

        return "user/board/noticeList";
    }

    @GetMapping( "/noticedetail")
    public String noticeDetail(@RequestParam("id") String id, Model model) {

        PostAndMemberDTO notice = noticeService.selectNotice(id);

        String text = notice.getPostContext().replace("\r\n", "<br>");

        notice.setPostContext(text);

        model.addAttribute("notice", notice);

        return "user/board/noticeDetail";
    }
}