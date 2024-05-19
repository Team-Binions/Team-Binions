package com.beanions.search.controller;

import com.beanions.board.common.dto.PostAndMemberDTO;
import com.beanions.common.dto.SearchDTO;
import com.beanions.common.paging.PagingResponse;
import com.beanions.search.service.SearchService;
import org.apache.ibatis.javassist.compiler.ast.Keyword;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public String searchResult(@ModelAttribute("params") final SearchDTO params, Model model) {

        String Keyword = params.getKeyword();
        System.out.println("keyword = " + params.getKeyword());
        System.out.println("searchType = " + params.getSearchType());

        List<PostAndMemberDTO> postList = searchService.searchResult(params);

        int count = searchService.count(params);

        System.out.println("count = " + count);

        for (PostAndMemberDTO post : postList) {
            System.out.println("post = " + post);
        }

        model.addAttribute("count", count);
        model.addAttribute("keyword", Keyword);
        model.addAttribute("searchType", params.getSearchType());
        model.addAttribute("postList", postList);

        return "/user/search/result";
    }
}
