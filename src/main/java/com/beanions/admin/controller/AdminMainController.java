package com.beanions.admin.controller;

import com.beanions.common.dto.PostDTO;
import com.beanions.admin.service.AdminService;
import com.beanions.admin.dto.AdminMainDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminMainController {

    private final AdminService adminService;

    public AdminMainController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin")
    public String selectDashBoard(Model model) {

        List<PostDTO> postList = adminService.selectCurrentBoard();
        List<PostDTO> magazineList = adminService.selectCurrentMagazine();
        List<AdminMainDTO> adminMainDataList = adminService.selectAdminMainData();

//        for (PostDTO post : postList) {
//            System.out.println("post = " + post);
//        }

        for (AdminMainDTO data : adminMainDataList) {
            System.out.println("data = " + data);
        }

        model.addAttribute("postList", postList);
        model.addAttribute("magazineList", magazineList);
        model.addAttribute("aminMainData", adminMainDataList.get(0));

        return "admin/main/dashBoard";
    }
}
