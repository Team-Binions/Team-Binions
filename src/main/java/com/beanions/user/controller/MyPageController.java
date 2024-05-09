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
  @GetMapping("/user/mypage")
  public String mypageMain(Model model){

    List<MyPageDTO> userMypageMainDataList = myPageService.selectMyPageMainData();

    System.out.println("logined user : "+ userMypageMainDataList);

    model.addAttribute("userMypageMainData", userMypageMainDataList.get(0));

    return "user/mypage/mypageMain";
  }

  // 마이페이지 > 예리뷰
  @GetMapping("/user/mypage/review")
  public String mypageReview(Model model){

    List<MyPageDTO> userMypageReivewDataList = myPageService.selectMyPageReviewData();

    System.out.println("REVIEW : "+ userMypageReivewDataList);
    System.out.println("REVIEWCount : "+ userMypageReivewDataList.get(0).getReviewCount());
    System.out.println();

    model.addAttribute("userMypageReivewData", userMypageReivewDataList.get(0));

    return "user/mypage/mypageReview";
  }

  // 마이페이지 > 예수다
  @GetMapping("/user/mypage/free")
  public String mypageFree(Model model){

    List<MyPageDTO> userMypageFreeDataList = myPageService.selectMyPageFreeData();

    System.out.println("Free : "+ userMypageFreeDataList);
    System.out.println("freeCount : "+ userMypageFreeDataList.get(0).getFreeCount());

    model.addAttribute("userMypageFreeData", userMypageFreeDataList.get(0));

    return "user/mypage/mypageFree";
  }

  // 마이페이지 > 댓글
  @GetMapping("/user/mypage/comment")
  public String mypageComment(Model model){

    List<MyPageDTO> userMypageCommentDataList = myPageService.selectMyPageCommentData();

    System.out.println("Comment : "+ userMypageCommentDataList);
    System.out.println("Comment length : "+ userMypageCommentDataList.size());

    model.addAttribute("userMypageCommentData", userMypageCommentDataList.get(0));

    return "user/mypage/mypageComment";
  }
}
