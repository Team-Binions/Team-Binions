package com.beanions.search.controller;

import com.beanions.search.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public String searchResult() {

        return "/user/search/result";
    }
}
