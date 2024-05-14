//package com.beanions.common.dto;
//
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//@Getter
//@ToString
//@Setter
//public class PageMaker {
//
//    private static final int PAGE_COUNT = 10;
//
//    private int beginPage, endPage;
//
//    private boolean prev, next;
//
//    private Page page;
//
//    private Long totalCount;
//
//    public PageMaker(Page page, Long totalCount){
//        this.page = page;
//        this.totalCount = totalCount;
//        makePageInfo();
//    }
//    private void makePageInfo(){
//        this.endPage = (int) Math.ceil(page.getPageNum() / (double) PAGE_COUNT) * PAGE_COUNT;
//        this.beginPage = endPage - PAGE_COUNT + 1;
//
//        int realEnd = (int) Math.ceil(totalCount / (double) page.getAmount());
//        if(realEnd < endPage){
//            this.endPage = realEnd;
//        }
//
//        this.prev = beginPage > 1;
//
//        this.next = endPage < realEnd;
//    }
//}
