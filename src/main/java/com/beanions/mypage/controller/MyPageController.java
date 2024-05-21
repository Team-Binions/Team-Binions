package com.beanions.mypage.controller;

import com.beanions.common.dto.MembersDTO;
import com.beanions.common.uploadfiles.UploadService;
import com.beanions.mypage.dto.MyPageDTO;
import com.beanions.mypage.dto.SchedulesDTO;
import com.beanions.mypage.service.MyPageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
//    List<MyPageDTO> userMypageCommentPostCategoryList = myPageService.selectMyPageCommentPostCategory((Integer)session.getAttribute("memberCode"));
    List<MyPageDTO> userMypageCommentPostCategoryList = myPageService.selectMyPageCommentPostCategory((int)session.getAttribute("memberCode"));

    System.out.println("category : " + userMypageCommentPostCategoryList);

    if (userMypageCommentDataList != null && !userMypageCommentDataList.isEmpty()){
      model.addAttribute("userMypageCommentData", userMypageCommentDataList.get(0));
      if (userMypageCommentPostCategoryList != null && !userMypageCommentPostCategoryList.isEmpty()){
        model.addAttribute("userMypageCommentCategory", userMypageCommentPostCategoryList);
        System.out.println("userMypageCommentPostCategoryList = " + userMypageCommentPostCategoryList);
        System.out.println("userMypageCommentPostCategoryList.get(0) = " + userMypageCommentPostCategoryList.get(0));
//        model.addAttribute("userMypageCommentCategory", userMypageCommentPostCategoryList.get(0));
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

    return "user/mypage/mypageMyinfo";
  }

  @PostMapping("/request-modify-member")
  @ResponseBody
  public ResponseEntity<String> modifyMemberInfo(@RequestBody MembersDTO member, HttpSession session){

    System.out.println("수정된 회원 정보 : " + member);
    int result = myPageService.modifyMemberInfo(member);
    if(result > 0){
      if ( member.getWeddingFile() == null ) {
        return ResponseEntity.ok().build();
      } else {
        // 파일 저장 로직
        String fileName = member.getWeddingFile();

        String root = "src/main/resources/assets/images/upload";

        String filePath = root + "/user/signupTemp/" + fileName;
        String copyFolderPath = root + "/user/verify/";

        File file = new File(filePath);

        try {
          // 기존 경로 폴더에 있던 파일을(Temp폴더) 해당하는 폴더로 복붙한다.
          Path sourcePath = Paths.get(filePath);
          System.out.println("임시파일 저장 폴더 : " + sourcePath);
          if (!Files.exists(sourcePath)) {
            // 파일이 존재하지 않는 경우 404 Not Found 응답 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
          }

          System.out.println(member);
          result = myPageService.modifyMemberInfo(member);

          Path destinationPath = Paths.get(copyFolderPath + file.getName());
          Files.move(sourcePath, destinationPath);
          System.out.println("복사된 폴더 경로 : " + destinationPath);

        } catch (IOException e) {
          System.out.println("error : " + e);
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
      }
      session.setAttribute("weddingFile",member.getWeddingFile());
      System.out.println("result : " + result);

      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  // 마이페이지 > 파일 업로드
  @GetMapping("/board/fileUpload")
  public String fileUpload(Model model, HttpSession session){

    int memberCode = (int) session.getAttribute("memberCode");

    model.addAttribute("memberCode",memberCode);

    return "user/mypage/fileUpload";
  }

  // 마이페이지 > 글쓰기
//  @GetMapping("/write")
//  public String writeBoard(HttpSession session, RedirectAttributes rttr, Model model){
//    Integer memberCode = (Integer) session.getAttribute("memberCode");
//    System.out.println("memberCode = " + memberCode);
//
//    model.addAttribute("memberCode", memberCode);
//    return "user/mypage/writeBoard";
//  }




  @PostMapping("/registBoard")
  public String registWriteBoard(RedirectAttributes rttr, Model model){

    myPageService.registWriting();

    return "redirect:/user/board/reviewList";
//    if (session.getAttribute("true") != null) {
//      // 기존 temp폴더에 저장된 이미지 표시를 위해 에디터에는 /temp로 경로가 지정되어 있다
//      // 이를 마지막 게시글 다음 번호로 설정한다.
//      content = content.replaceAll("/temp", "/" + board_num);
//
//      // temp 폴더 안의 이미지를 게시글 저장소로 이동
//      String path_folder1 = realPath + "/upload/image/fileupload/temp /";
//      String path_folder2 = realPath + "/upload/image/fileupload/" + board_num + "/";
//
//      // 폴더 복사 함수
//      fileUpload(path_folder1, path_folder2);
//
//      // ...
//    }
  }

  @PostMapping("/mypage/withdraw")
  public String deleteInfo(@RequestParam("id") String id, RedirectAttributes rttr) {


    return "redirect:/";
  }
}
