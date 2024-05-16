package com.beanions.admin.controller;


import com.beanions.admin.dto.AdminPostDTO;
import com.beanions.common.dto.MembersDTO;
import com.beanions.admin.service.AdminMemberService;
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
public class AdminController {

    private final AdminMemberService adminMemberService;

    public AdminController(AdminMemberService adminMemberService) {
        this.adminMemberService = adminMemberService;
    }

    @GetMapping("/member")
    public String findMembersList(Model model) {

        List<MembersDTO> membersList = adminMemberService.membersAllList();

        for (MembersDTO members : membersList) {
            System.out.println("members = " + members);
        }

        model.addAttribute("membersList", membersList);

        return "/admin/member/membersList";
    }

    @GetMapping("/member/membersDetail")
    public String memberDetail(@RequestParam("id") String id, Model model) {
        System.out.println("id = " + id);

        int codes = Integer.parseInt(id);

        MembersDTO memberDetail = adminMemberService.selectMembers(codes);
        List<PostDTO> memberOnePosts = adminMemberService.selectMemberPost(codes);

        for (PostDTO memberOne : memberOnePosts) {
            System.out.println("memberOne = " + memberOne);
        }

        System.out.println("memberDetail = " + memberDetail);
        System.out.println("memberOnePosts = " + memberOnePosts);

        model.addAttribute("memberDetail", memberDetail);
        model.addAttribute("memberOnePosts", memberOnePosts);

        return "admin/member/membersDetail";
    }


    //    @PostMapping("member/membersModify/{id}")
    @PostMapping("/member/update")
    public String memberModify(MembersDTO modify, RedirectAttributes rttr) {

        System.out.println("modify = " + modify);

        adminMemberService.memberModify(modify);


        rttr.addFlashAttribute("successMessage", "해당 회원의 정보가 수정 되었습니다.");

        return "redirect:/admin/member";
    }

    @PostMapping("/member/delete")
    public String memberDelete(@RequestParam("id") String id, RedirectAttributes rttr) {

        int memberCode = Integer.parseInt(id);

        adminMemberService.memberDelete(memberCode);

        rttr.addFlashAttribute("successMessage", memberCode + "번 회원을 삭제하셨습니다.");

        return "redirect:/admin/member";
    }
}
