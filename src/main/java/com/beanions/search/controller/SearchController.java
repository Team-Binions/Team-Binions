package com.beanions.search.controller;

import com.beanions.board.common.dto.PostAndMemberDTO;
import com.beanions.search.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public String searchResult(@RequestParam("keyword") String keyword, Model model) {

        System.out.println("keyword = " + keyword);

        List<PostAndMemberDTO> postList = searchService.searchResult(keyword);

        for (PostAndMemberDTO post : postList ) {
            System.out.println("data = " + post);
        }

        return "/user/search/result";
    }
}
