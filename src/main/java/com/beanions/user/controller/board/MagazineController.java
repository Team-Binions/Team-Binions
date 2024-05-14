package com.beanions.user.controller.board;

import com.beanions.common.dto.PostAndMemberDTO;
import com.beanions.user.service.board.MagazineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board")
public class MagazineController {
    private final MagazineService magazineService;

    public MagazineController (MagazineService magazineService){
        this.magazineService = magazineService;
    }

    @GetMapping("/magazine")
    public String magazineList(Model model) {

        List<PostAndMemberDTO> magazineList = magazineService.allMagazineList();

        model.addAttribute("magazineList", magazineList);

        return "/user/board/magazineList";
    }

    @GetMapping( "/magazinedetail")
    public String magazineDetail(@RequestParam("id") String id, Model model) {

        List<PostAndMemberDTO> megazine = magazineService.selectMagazine(id);

        model.addAttribute("magazine", megazine);

        return "/user/board/magazineDetail";
    }
}
