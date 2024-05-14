package com.beanions.user.controller;

import com.beanions.common.dto.MyPageDTO;
import com.beanions.common.dto.SchedulesDTO;
import com.beanions.user.service.MyPageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    model.addAttribute("userMypageCommentData", userMypageCommentDataList.get(0));
    model.addAttribute("userMypageCommentCategory", userMypageCommentPostCategoryList);

    return "user/mypage/mypageComment";
  }

  // 마이페이지 > 스케쥴 관리
  @GetMapping("/mypage/schedule")
  public String mypageSchedule(Model model){
    List<MyPageDTO> userMypageScheduleInfo = myPageService.selectMyPageScheduleInfo();
    model.addAttribute("userMypageScheduleInfo", userMypageScheduleInfo.get(0));

    return "user/mypage/mypageSchedule";
  }

  // 마이페이지 > 스케쥴 상세 페이지
  @GetMapping("/mypage/scheduleDetail")
  public String mypageScheduleDetail(@RequestParam("id") String id, Model model){
    List<MyPageDTO> userMypageScheduleInfo = myPageService.selectScheduleDetail(id);
    List<SchedulesDTO> userScheduleInfo = userMypageScheduleInfo.get(0).getSchedules();
    model.addAttribute("userMypageInfo", userMypageScheduleInfo.get(0));
    model.addAttribute("userScheduleInfo", userScheduleInfo.get(0));

    return "user/mypage/mypageScheduleDetail";
  }

  // 마이페이지 > 스케쥴 등록
  @GetMapping("/mypage/scheduleRegister")
  public String mypageScheduleRegister(Model model){
    List<MyPageDTO> userMypageScheduleInfo = myPageService.selectMyPageScheduleInfo();
    model.addAttribute("userMypageScheduleInfo", userMypageScheduleInfo.get(0));

    return "user/mypage/mypageScheduleRegister";
  }

  // 마이페이지 > 스케쥴 등록 post
  @PostMapping("/mypage/scheduleRegister")
  public String mypageRegisterSchedule(SchedulesDTO newSchedule, RedirectAttributes rttr){
    myPageService.registNewSchedule(newSchedule);
    //rttr.addFlashAttribute("successMessage", "신규 메뉴 등록 성공\uD83C\uDF8A");
    return "redirect:/mypage/schedule";
  }

  // 마이페이지 > 스케쥴 삭제 post
  @PostMapping("/mypage/deleteSchedule/{scheduleCode}")
  public String mypageDeleteSchedule(@RequestParam("scheduleCode") int code, RedirectAttributes rttr){
    myPageService.deleteSchedule(code);
    //rttr.addFlashAttribute("successMessage", "신규 메뉴 등록 성공\uD83C\uDF8A");
    return "redirect:/mypage/schedule";
  }

  // 마이페이지 > 스케쥴 수정
  @GetMapping("/mypage/scheduleManage")
  public String mypageScheduleManage(@RequestParam("id") String id, Model model){
    List<MyPageDTO> userMypageScheduleInfo = myPageService.selectScheduleDetail(id);
    List<SchedulesDTO> userScheduleInfo = userMypageScheduleInfo.get(0).getSchedules();
    model.addAttribute("userMypageInfo", userMypageScheduleInfo.get(0));
    model.addAttribute("userScheduleInfo", userScheduleInfo.get(0));
    model.addAttribute("scheduleDate", userScheduleInfo.get(0).getScheduleDate());

    return "user/mypage/mypageScheduleManage";
  }

  // 마이페이지 > 스케쥴 수정 update
  @PostMapping("/mypage/scheduleManage")
  public String mypageUpdateSchedule(SchedulesDTO modifiedSchedule, RedirectAttributes rttr){
    myPageService.modifySchedule(modifiedSchedule);
    return "redirect:/mypage/schedule";
  }

  // 마이페이지 > 회원 정보 수정
  @GetMapping("/mypage/myinfo")
  public String mypageMyinfo(Model model){
    return "user/mypage/mypageMyinfo";
  }
}
