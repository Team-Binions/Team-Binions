//package com.beanions.common.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.ToString;
//
//@ToString
//@Getter
//@AllArgsConstructor
//public class Page {
//
//    private Long pageNum;
//    private int amount;
//    public Page(){
//        this.pageNum = 1l;
//        this.amount = 9;
//    }
//
//    public void setPageNum(Long pageNum){
//        if(pageNum < 1){
//            this.pageNum = 1l;
//            return;
//        }
//        this.pageNum = pageNum;
//    }
//
//    public void setAmount(int amount){
//        if(amount < 9 || amount > 100){
//            this.amount = 10;
//            return;
//        }
//        this.amount = amount;
//    }
//}
