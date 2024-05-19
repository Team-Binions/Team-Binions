package com.beanions.mypage.controller;

import com.beanions.common.dto.MembersDTO;
import com.beanions.mypage.dto.MyPageDTO;
import com.beanions.mypage.dto.SchedulesDTO;
import com.beanions.mypage.service.MyPageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes
public class MyPageController {

  private final MyPageService myPageService;

  public MyPageController(MyPageService myPageService) {
    this.myPageService = myPageService;
  }

  // 마이페이지 main
  @GetMapping("/mypage")
  public String mypageMain(Model model,HttpSession session){

    List<MyPageDTO> userMypageMainDataList = myPageService.selectMyPageMainData((int) session.getAttribute("memberCode"));

    model.addAttribute("userMypageMainData", userMypageMainDataList.get(0));

    return "user/mypage/mypageMain";
  }

  // 마이페이지 > 예리뷰
  @GetMapping("/mypage/review")
  public String mypageReview(Model model,HttpSession session){

    List<MyPageDTO> userMypageReivewDataList = myPageService.selectMyPageReviewData((int)session.getAttribute("memberCode"));

    if (userMypageReivewDataList != null && !userMypageReivewDataList.isEmpty()){
      model.addAttribute("userMypageReivewData", userMypageReivewDataList.get(0));
    } else {
      MyPageDTO myPageDTO = new MyPageDTO((String)session.getAttribute("nickname"),0,0,0,0,0,0,null,null,null,null);
      model.addAttribute("userMypageReivewData", myPageDTO);
    }

    return "user/mypage/mypageReview";
  }

  // 마이페이지 > 예수다
  @GetMapping("/mypage/free")
  public String mypageFree(Model model,HttpSession session){

    List<MyPageDTO> userMypageFreeDataList = myPageService.selectMyPageFreeData((int)session.getAttribute("memberCode"));

    if ( userMypageFreeDataList != null && !userMypageFreeDataList.isEmpty()){
      model.addAttribute("userMypageFreeData", userMypageFreeDataList.get(0));
    } else {
      MyPageDTO myPageDTO = new MyPageDTO((String)session.getAttribute("nickname"),0,0,0,0,0,0,null,null,null,null);
      model.addAttribute("userMypageFreeData", myPageDTO);
    }

    return "user/mypage/mypageFree";
  }

  // 마이페이지 > 댓글
  @GetMapping("/mypage/comment")
  public String mypageComment(Model model,HttpSession session){

    List<MyPageDTO> userMypageCommentDataList = myPageService.selectMyPageCommentData((int)session.getAttribute("memberCode"));
    List<MyPageDTO> userMypageCommentPostCategoryList = myPageService.selectMyPageCommentPostCategory((Integer)session.getAttribute("memberCode"));

    System.out.println("category : " + userMypageCommentPostCategoryList);

    if (userMypageCommentDataList != null && !userMypageCommentDataList.isEmpty()){
      model.addAttribute("userMypageCommentData", userMypageCommentDataList.get(0));
      if (userMypageCommentPostCategoryList != null && !userMypageCommentPostCategoryList.isEmpty()){
        model.addAttribute("userMypageCommentCategory", userMypageCommentPostCategoryList.get(0));
      } else {
        MyPageDTO myPageDTO = new MyPageDTO((String)session.getAttribute("nickname"),0,0,0,0,0,0,null,null,null,null);
        model.addAttribute("userMypageCommentCategory", myPageDTO);
      }
    }
    else {
        MyPageDTO myPageDTO = new MyPageDTO((String)session.getAttribute("nickname"),0,0,0,0,0,0,null,null,null,null);
        model.addAttribute("userMypageCommentData", myPageDTO);
    }

    return "user/mypage/mypageComment";
  }

  // 마이페이지 > 스케쥴 관리
  @GetMapping("/mypage/schedule")
  public String mypageSchedule(Model model,HttpSession session){

    List<MyPageDTO> userMypageScheduleInfo = myPageService.selectMyPageScheduleInfo((int)session.getAttribute("memberCode"));

    if(userMypageScheduleInfo != null && !userMypageScheduleInfo.isEmpty()){
      model.addAttribute("userMypageScheduleInfo", userMypageScheduleInfo.get(0));
    } else {
      MyPageDTO myPageDTO = new MyPageDTO((String)session.getAttribute("nickname"),0,0,0,0,0,0,null,null,null,null);
      model.addAttribute("userMypageScheduleInfo", myPageDTO);
    }

    return "user/mypage/mypageSchedule";
  }

  // 마이페이지 > 스케쥴 상세 페이지
  @GetMapping("/mypage/scheduleDetail")
  public String mypageScheduleDetail(@RequestParam("id") String id, Model model, HttpSession session){

    List<MyPageDTO> userMypageScheduleInfo = myPageService.selectScheduleDetail((int)session.getAttribute("memberCode"),id);

    if (userMypageScheduleInfo != null && !userMypageScheduleInfo.isEmpty()){

      model.addAttribute("userMypageInfo", userMypageScheduleInfo.get(0));
      List<SchedulesDTO> userScheduleInfo = userMypageScheduleInfo.get(0).getSchedules();

      if(userScheduleInfo != null && !userScheduleInfo.isEmpty()){
        model.addAttribute("userScheduleInfo", userScheduleInfo.get(0));
      } else {
        SchedulesDTO schedulesDTO = new SchedulesDTO(null,null,null,null,null);
        model.addAttribute("userScheduleInfo", schedulesDTO);
      }
    }
    else {
      List<SchedulesDTO> schedulesDTO = new ArrayList<>();
      schedulesDTO.add(new SchedulesDTO(null,(int)session.getAttribute("memberCode"),null,null,null));
      MyPageDTO myPageDTO = new MyPageDTO((String)session.getAttribute("nickname"),0,0,0,0,0,0,null,null,null,schedulesDTO);
      model.addAttribute("userMypageInfo", myPageDTO);
    }


    return "user/mypage/mypageScheduleDetail";
  }

  // 마이페이지 > 스케쥴 등록
  @GetMapping("/mypage/scheduleRegister")
  public String mypageScheduleRegister(Model model,HttpSession session){

    List<MyPageDTO> userMypageScheduleInfo = myPageService.selectMyPageScheduleInfo((int)session.getAttribute("memberCode"));

    if ( userMypageScheduleInfo != null && !userMypageScheduleInfo.isEmpty()){
      model.addAttribute("userMypageScheduleInfo", userMypageScheduleInfo.get(0));
    } else {
      List<SchedulesDTO> schedulesDTO = new ArrayList<>();
      schedulesDTO.add(new SchedulesDTO(null,(int)session.getAttribute("memberCode"),null,null,null));
      MyPageDTO myPageDTO = new MyPageDTO((String)session.getAttribute("nickname"),0,0,0,0,0,0,null,null,null,schedulesDTO);
      model.addAttribute("userMypageScheduleInfo", myPageDTO);
    }

    return "user/mypage/mypageScheduleRegister";
  }

  // 마이페이지 > 스케쥴 등록 post
  @PostMapping("/mypage/scheduleRegister")
  public String mypageRegisterSchedule(SchedulesDTO newSchedule){
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
  public String mypageScheduleManage(@RequestParam("id") String id, Model model, HttpSession session){
    List<MyPageDTO> userMypageScheduleInfo = myPageService.selectScheduleDetail((int)session.getAttribute("memberCode"),id);

    if ( userMypageScheduleInfo != null && !userMypageScheduleInfo.isEmpty()){
      model.addAttribute("userMypageInfo", userMypageScheduleInfo.get(0));

      List<SchedulesDTO> userScheduleInfo = userMypageScheduleInfo.get(0).getSchedules();
      if (userScheduleInfo != null && !userScheduleInfo.isEmpty()){
        model.addAttribute("userScheduleInfo", userScheduleInfo.get(0));
        model.addAttribute("scheduleDate", userScheduleInfo.get(0).getScheduleDate());
      } else {
        SchedulesDTO schedulesDTO = new SchedulesDTO(null,null,null,null,null);
        model.addAttribute("userScheduleInfo",schedulesDTO);
        model.addAttribute("scheduleDate",schedulesDTO.getScheduleDate());
      }
    } else {
      MyPageDTO myPageDTO = new MyPageDTO((String)session.getAttribute("nickname"),0,0,0,0,0,0,null,null,null,null);
      model.addAttribute("userMypageInfo", myPageDTO);
    }

    return "user/mypage/mypageScheduleManage";
  }

  // 마이페이지 > 스케쥴 수정 update
  @PostMapping("/mypage/scheduleManage")
  public String mypageUpdateSchedule(SchedulesDTO modifiedSchedule){
    myPageService.modifySchedule(modifiedSchedule);
    return "redirect:/mypage/schedule";
  }

  // 마이페이지 > 회원 정보 수정
  @GetMapping("/mypage/myinfo")
  public String mypageMyinfo(Model model, HttpSession session){

    MyPageDTO myPageDTO = myPageService.selectMyPostInfo((int)session.getAttribute("memberCode"));

    int totalCount = myPageDTO.getTotalCount();
    model.addAttribute("totalCount",totalCount);

    String memberId = (String) session.getAttribute("memberId");
    String memberPw = (String) session.getAttribute("memberPw");
    String nickname = (String) session.getAttribute("nickname");
    String email = (String) session.getAttribute("email");
    String phone = (String) session.getAttribute("phone");
    String gender = (String) session.getAttribute("gender");
    String marriedStatus = (String) session.getAttribute("marriedStatus");
    String weddingFile = (String) session.getAttribute("weddingFile");
    String weddingVerified = (String) session.getAttribute("weddingVerified");
    Date signupDate = (Date) session.getAttribute("signupDate");

    model.addAttribute("memberId",memberId);
    model.addAttribute("memberPw",memberPw);
    model.addAttribute("nickname",nickname);
    model.addAttribute("email",email);
    model.addAttribute("phone",phone);
    model.addAttribute("gender",gender);
    model.addAttribute("marriedStatus",marriedStatus);
    model.addAttribute("weddingFile",weddingFile);
    model.addAttribute("weddingVerified",weddingVerified);
    model.addAttribute("signupDate",signupDate);

    return "user/mypage/mypageMyinfo";
  }

  // 마이페이지 > 파일 업로드
  @GetMapping("/fileupload")
  public String fileUpload(Model model){
    return "user/mypage/fileUpload";
  }

  // 마이페이지 > 글쓰기
  @GetMapping("/write")
  public String writeBoard(Model model){
    return "user/mypage/writeBoard";
  }
}
