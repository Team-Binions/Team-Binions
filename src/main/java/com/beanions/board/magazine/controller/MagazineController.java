package com.beanions.board.magazine.controller;

import com.beanions.board.common.dto.PostAndMemberDTO;
import com.beanions.board.magazine.service.MagazineService;
import com.beanions.common.dto.SearchDTO;
import com.beanions.common.paging.PagingResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String magazineList(Model model, @ModelAttribute("params") final SearchDTO params) {

        PagingResponse<PostAndMemberDTO> magazineList = magazineService.allMagazineList(params);

        model.addAttribute("magazineList", magazineList);

        return "/user/board/magazineList";
    }

    @GetMapping( "/magazinedetail")
    public String magazineDetail(@RequestParam("id") String id, Model model) {

        List<PostAndMemberDTO> megazine = magazineService.selectMagazine(id);

        model.addAttribute("magazine", megazine.get(0));

        return "/user/board/magazineDetail";
    }
}
