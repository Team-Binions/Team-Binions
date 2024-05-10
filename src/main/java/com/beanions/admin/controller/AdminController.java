package com.beanions.admin.controller;


import com.beanions.admin.service.MemberService;
import com.beanions.common.dto.MembersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/member")
public class AdminController {

    private final MemberService memberService;

    public AdminController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/membersList")
    public String findMembersList(Model model) {

        List<MembersDTO> membersList = memberService.membersAllList();

        for (MembersDTO members : membersList) {
            System.out.println("members = " + members);
        }

        model.addAttribute("membersList", membersList);

        return "admin/member/membersList";
    }

    @GetMapping("/membersModify")
    public void modifyPage() {}

    @PostMapping("/membersModify")
    public String modifyMembers(MembersDTO modify, RedirectAttributes rttr) {

        memberService.modifyOneMember(modify);

        rttr.addFlashAttribute("successMessage", "회원정보가 수정되었습니다.");

        return "redirect:/admin/member/membersModify";
    }


}
