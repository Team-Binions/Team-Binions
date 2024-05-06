package com.beanions.admin.controller;

import com.beanions.admin.service.AdminService;
import com.beanions.common.dto.AdminPostDTO;
import com.beanions.common.dto.PostDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        return "/admin/post/freeDetail";
    }

    @GetMapping("/post/update")
    public String postUpdate(@RequestParam("id") String id, Model model) {

        System.out.println("id = " + id);

        int code = Integer.parseInt(id);

        List<AdminPostDTO> postDetail = adminService.selectPost(code);

        System.out.println("postDetail = " + postDetail);

        model.addAttribute("postDetail", postDetail);

        return "/admin/post/update";
    }

//    @PostMapping("post/update/{id}")
    @PostMapping("post/update")
    public String postUpdate(PostDTO post, RedirectAttributes rttr) {

//        System.out.println("id = " + id);

//        int code = Integer.parseInt(id);
        System.out.println("post = " + post);

        adminService.postUpdate(post);


        rttr.addFlashAttribute("successMessage", "게시글 수정 성공");

        return "redirect:/admin/post";
    }

    @PostMapping("/post/delete")
    public String postDelete(@RequestParam("id") String id, RedirectAttributes rttr) {

        int postCode = Integer.parseInt(id);

        adminService.postDelete(postCode);

        rttr.addFlashAttribute("successMessage", postCode + "번 메뉴 삭제 성공!");

        return "redirect:/admin/post";
    }


//    @PostMapping("/post")
//    public String postDelete(int id, RedirectAttributes rttr) {
//
//        adminService.postDelete(id);
//
//        rttr.addFlashAttribute("successMessage", id + "번 메뉴 삭제 성공!");
//
//        return "redirect:/admin/post";
//    }
}
