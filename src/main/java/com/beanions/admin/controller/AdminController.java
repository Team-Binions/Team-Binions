package com.beanions.admin.controller;

import com.beanions.admin.dto.AdminMemberPostDTO;
import com.beanions.common.dto.MembersDTO;
import com.beanions.admin.service.AdminMemberService;
import com.beanions.common.dto.PostDTO;
import com.beanions.common.dto.SearchDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminMemberService adminMemberService;

    public AdminController(AdminMemberService adminMemberService) {
        this.adminMemberService = adminMemberService;
    }

    @GetMapping("/member")
    public String findMembersList(@ModelAttribute("params") final SearchDTO params, Model model) {

        String Keyword = params.getKeyword();
        System.out.println("keyword = " + params.getKeyword());

        List<MembersDTO> membersList = adminMemberService.membersAllList(params);

        for (MembersDTO members : membersList) {
            System.out.println("members = " + members);
        }

        model.addAttribute("keyword", Keyword);
        model.addAttribute("membersList", membersList);


        return "/admin/member/membersList";
    }

    @GetMapping("/member/membersDetail")
    public String memberDetail(@RequestParam("id") String id, Model model) {
        System.out.println("id = " + id);

        int codes = Integer.parseInt(id);

        MembersDTO memberDetail = adminMemberService.selectMembers(codes);
        List<PostDTO> memberOnePosts = adminMemberService.selectMemberPost(codes);
        AdminMemberPostDTO usersReviewDataList = adminMemberService.selectAdminReviewData(codes);

        System.out.println("조회 된 회원 : " + memberDetail);
        System.out.println("codes = " + codes);

        model.addAttribute("memberDetail", memberDetail);
        model.addAttribute("memberOnePosts", memberOnePosts);
        model.addAttribute("usersReviewDataList", usersReviewDataList);

        return "admin/member/membersDetail";
    }

    @GetMapping("/member/membersModify")
    public String memberModify(@RequestParam("id") String id, Model model) {

        System.out.println("id = " + id);

        int code = Integer.parseInt(id);

        MembersDTO memberModify = adminMemberService.selectMembers(code);
        AdminMemberPostDTO userWritePostCount = adminMemberService.selectAdminReviewData(code);

        System.out.println("memberModify = " + memberModify);

        model.addAttribute("memberModify", memberModify);
        model.addAttribute("userWritePostCount", userWritePostCount);

        return "/admin/member/membersModify";
    }

    //    @PostMapping("member/membersModify/{id}")

    @PostMapping(value="/request-verify-wedd-admin")
    @ResponseBody
    public String requestUploadVefFile(@RequestParam(value="file",required = false) MultipartFile file) throws Exception {

        /* 서버로 전달 된 File을 서버에서 설정하는 경로에 저장한다. */
        String root = "src/main/resources/assets/images/upload";
        String filePath = root + "/user/signupTemp";

        File dir = new File(filePath);

        if( !dir.exists() ) {
            dir.mkdirs();
        }

        /* 파일명 변경하기 */
        String originFileName = file.getOriginalFilename();
        System.out.println("originFileName : " + originFileName);
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
//        System.out.println("ext : " + ext);

        String savedName = UUID.randomUUID() + ext;
        System.out.println("savedName : " + savedName);

        /* 파일 저장 */
        try {
            Path path= Paths.get(filePath + "/" + savedName).toAbsolutePath();
            file.transferTo(path.toFile());
//            file.transferTo(new File(filePath + "/" + savedName));

        } catch (IOException e) {
            System.out.println("error : " + e);
        }

        return new ObjectMapper().writeValueAsString(savedName);
    }
    @PostMapping("/member/update")
    public Object memberModify(MembersDTO modify, RedirectAttributes rttr) {

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
