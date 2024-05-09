package com.beanions.user.controller;

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

  @GetMapping("/user/mypage")
  public String mypageMain(Model model){

    List<MyPageDTO> userMypageMainDataList = myPageService.selectMyPageMainData();

    System.out.println("logined user : "+ userMypageMainDataList);
    System.out.println("logined user : "+ userMypageMainDataList.get(0).getNickname());

//    List<MyPageDTO> allDataList = myPageService.selectAllMyPageData();
//
//    for (MyPageDTO mypage : allDataList) {
//      System.out.println("mypage = " + mypage);
//    }
    model.addAttribute("userMypageMainData", userMypageMainDataList.get(0));

    return "user/mypage/mypageMain";
  }
}
