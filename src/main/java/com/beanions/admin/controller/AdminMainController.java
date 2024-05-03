package com.beanions.admin.controller;

import com.beanions.admin.service.AdminService;
import com.beanions.common.dto.AdminPostDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminMainController {

    private final AdminService adminService;

    public AdminMainController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/post")
    public String selectAllPost(Model model) {

        List<AdminPostDTO> postList = adminService.selectAllPost();

        for (AdminPostDTO post : postList) {
            System.out.println("post = " + post);
        }

        model.addAttribute("postList", postList);

        return "/admin/post/list";
    }

    @GetMapping("/post/detail")
    public String postDetail(@RequestParam("id") String id, Model model) {

        System.out.println("id = " + id);

        int code = Integer.parseInt(id);

        List<AdminPostDTO> postDetail = adminService.selectPost(code);

        System.out.println("postDetail = " + postDetail);

        model.addAttribute("postDetail", postDetail);

        return "freeDetail";
    }
}
