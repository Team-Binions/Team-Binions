//package com.beanions.user.controller.board;
//
//import com.beanions.common.dto.Page;
//import com.beanions.common.dto.PageMaker;
//import com.beanions.user.service.board.FreeBoardService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.Map;
//
//@Controller
//public class PageController {
//
//    @GetMapping("/user/board")
//    public String Page(Page page, Model model){
//
//        Map<String, Object> allService = FreeBoardService.freeList(page);
//        PageMaker postcnt = new PageMaker(new Page(page.getPageNum(), page.getAmount()), (long) allService.get("postcnt"));
//
//        model.addAttribute("postcnt", allService.get("plist"));
//        model.addAttribute("pm", postcnt);
//
//        return "/freeList";
//    }
//}
