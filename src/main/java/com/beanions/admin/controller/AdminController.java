package com.beanions.admin.controller;


import com.beanions.admin.service.MemberService;
import com.beanions.common.dto.AdminPostDTO;
import com.beanions.common.dto.MembersDTO;
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

    private final MemberService memberService;

    public AdminController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member")
    public String findMembersList(Model model) {

        List<MembersDTO> membersList = memberService.membersAllList();

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

        List<MembersDTO> memberDetail = memberService.selectMembers(codes);

        System.out.println("memberDetail = " + memberDetail);

        model.addAttribute("memberDetail", memberDetail);

        return "/admin/member/membersDetail";
    }
    @GetMapping("/member/membersModify")
    public String memberModify(@RequestParam("id") String id, Model model) {

        System.out.println("id = " + id);

        int code = Integer.parseInt(id);

        List<MembersDTO> memberModify = memberService.selectMembers(code);

        System.out.println("memberModify = " + memberModify);

        model.addAttribute("memberModify", memberModify);

        return "/admin/member/membersModify";
    }

    //    @PostMapping("member/membersModify/{id}")
    @PostMapping("/member/update")
    public String memberModify(MembersDTO modify, RedirectAttributes rttr) {

        System.out.println("modify = " + modify);

        memberService.memberModify(modify);


        rttr.addFlashAttribute("successMessage", "해당 회원의 정보가 수정 되었습니다.");

        return "redirect:/admin/member";
    }

    @PostMapping("/member/delete")
    public String memberDelete(@RequestParam("id") String id, RedirectAttributes rttr) {

        int memberCode = Integer.parseInt(id);

        memberService.memberDelete(memberCode);

        rttr.addFlashAttribute("successMessage", memberCode + "번 회원을 삭제하셨습니다.");

        return "redirect:/admin/member";
    }

//    @GetMapping("/member/membersDetail")
//    public String memberPostDetail(@RequestParam("id") String id, Model model) {
//        System.out.println("id = " + id);
//
//        int codes = Integer.parseInt(id);
//
//        List<AdminPostDTO> adminMemberOnePost = memberService.oneMemberPost(codes);
//
//        for (AdminPostDTO memberPost : adminMemberOnePost) {
//            System.out.println("memberPost = " + memberPost);
//        }
//
//        model.addAttribute("adminMemberOnePost", adminMemberOnePost);
//
//        return "/admin/member/membersDetail";
//    }

}
