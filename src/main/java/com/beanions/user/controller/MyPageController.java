package com.beanions.user.controller;

import com.beanions.common.dto.CommentsDTO;
import com.beanions.common.dto.MyPageDTO;
import com.beanions.user.service.MyPageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MyPageController {

  private final MyPageService myPageService;

  public MyPageController(MyPageService myPageService) {
    this.myPageService = myPageService;
  }

  // 마이페이지 main
  @GetMapping("/mypage")
  public String mypageMain(Model model){

    List<MyPageDTO> userMypageMainDataList = myPageService.selectMyPageMainData();

    model.addAttribute("userMypageMainData", userMypageMainDataList.get(0));

    return "user/mypage/mypageMain";
  }

  // 마이페이지 > 예리뷰
  @GetMapping("/mypage/review")
  public String mypageReview(Model model){

    List<MyPageDTO> userMypageReivewDataList = myPageService.selectMyPageReviewData();

    model.addAttribute("userMypageReivewData", userMypageReivewDataList.get(0));

    return "user/mypage/mypageReview";
  }

  // 마이페이지 > 예수다
  @GetMapping("/mypage/free")
  public String mypageFree(Model model){

    List<MyPageDTO> userMypageFreeDataList = myPageService.selectMyPageFreeData();

    model.addAttribute("userMypageFreeData", userMypageFreeDataList.get(0));

    return "user/mypage/mypageFree";
  }

  // 마이페이지 > 댓글
  @GetMapping("/mypage/comment")
  public String mypageComment(Model model){

    List<MyPageDTO> userMypageCommentDataList = myPageService.selectMyPageCommentData();
    List<MyPageDTO> userMypageCommentPostCategoryList = myPageService.selectMyPageCommentPostCategory();

    System.out.println("category : " + userMypageCommentPostCategoryList);

    model.addAttribute("userMypageCommentData", userMypageCommentDataList.get(0));
    model.addAttribute("userMypageCommentCategory", userMypageCommentPostCategoryList.get(0));

    return "user/mypage/mypageComment";
  }

  // 마이페이지 > 스케쥴 관리
  @GetMapping("/mypage/schedule")
  public String mypageSchedule(Model model){

//    List<MyPageDTO> userMypageCommentDataList = myPageService.selectMyPageCommentData();
//    List<MyPageDTO> userMypageCommentPostCategoryList = myPageService.selectMyPageCommentPostCategory();
//
//    System.out.println("category : " + userMypageCommentPostCategoryList);
//
//    model.addAttribute("userMypageCommentData", userMypageCommentDataList.get(0));
//    model.addAttribute("userMypageCommentCategory", userMypageCommentPostCategoryList.get(0));

    return "user/mypage/mypageSchedule";
  }

  // 마이페이지 > 스케쥴 관리
  @GetMapping("/mypage/scheduleRegister")
  public String mypageScheduleRegister(Model model){
    return "user/mypage/mypageScheduleRegister";
  }

  // 마이페이지 > 스케쥴 관리
  @GetMapping("/mypage/scheduleManage")
  public String mypageScheduleManage(Model model){
    return "user/mypage/mypageScheduleManage";
  }

  // 마이페이지 > 회원 정보 수정
  @GetMapping("/mypage/myinfo")
  public String mypageMyinfo(Model model){
    return "user/mypage/mypageMyinfo";
  }
}
