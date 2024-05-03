package com.beanions.common.controller;

import com.beanions.common.dto.PostDTO;
import com.beanions.common.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController (NoticeService noticeService){
        this.noticeService = noticeService;
    }

    @GetMapping("/board")
    public String noticeList(Model model) {

        List<PostDTO> noticeList = noticeService.allNoticeList();

        model.addAttribute("noticeList", noticeList);

        return "user/noticeboard";
    }

    @GetMapping( "/board/detail")
    public String noticeDetail(@RequestParam("id") String id, Model model) {

        List<PostDTO> notice = noticeService.selectNotice(id);

        model.addAttribute("notice", notice);

        return "user/noticeboarddetail";
    }


}
