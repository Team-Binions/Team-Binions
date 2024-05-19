package com.beanions.common.uploadfiles;

import com.beanions.common.dto.FilesDTO;
import com.beanions.common.dto.PostDTO;
import com.beanions.common.dto.UploadFilesDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@Log4j2
public class UploadController {
  @Value("${spring.file.upload.path}") // application 의 properties 의 변수
  //src/main/resources/assets/images/upload
  private String uploadPath;

  private final UploadService uploadService;

  public UploadController(UploadService uploadService) {
    this.uploadService = uploadService;
  }


//  @GetMapping("/write")
//  public String postWriteBoard(HttpSession session, RedirectAttributes rttr, Model model) {
//
//    Integer memberCode = (Integer) session.getAttribute("memberCode");
//
//    model.addAttribute("memberCode", memberCode);
//
//    //return "/mypage/writeBoard";
//    return "user/mypage/writeBoard";
////    if (memberCode != null) {
////      // 회원 아이디를 글쓰기 페이지로 리다이렉트하면서 전달
////      return "/writeboard";
//////      return "/user/board/freeRegist";
////    } else {
////      // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
////      rttr.addFlashAttribute("errorMessage", "로그인 후에 게시글을 작성할 수 있습니다.");
////      return "redirect:/login";
////    }
//  }

  @PostMapping("/registBoard")
  public String registWriteBoard(RedirectAttributes rttr, Model model){

    uploadService.registWriting();

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







  /*파일 업로드, 업로드 결과 반환*/
  @PostMapping("/uploadAjax")
  public ResponseEntity<List<UploadFilesDTO>> uploadFile(MultipartFile[] uploadFiles) {

    List<UploadFilesDTO> resultDTOList = new ArrayList<>();

    for (MultipartFile uploadFile: uploadFiles) {

      // 이미지 파일만 업로드
      if (!Objects.requireNonNull(uploadFile.getContentType()).startsWith("image")) {
        log.warn("this file is not image type");
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
      }

      //List<FilesDTO> uploadFileList = uploadService.registerFileSelected();

      // 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로 => 바뀐 듯 ..
      String orginalName = uploadFile.getOriginalFilename();
      assert orginalName != null;
      String fileName = orginalName.substring(orginalName.lastIndexOf("\\") + 1);

      log.info("fileName: "+fileName);

      // 날짜 폴더 생성
      String folderPath = makeFolder();

      // UUID
      String uuid = UUID.randomUUID().toString();

      // 저장할 파일 이름 중간에 "_"를 이용해서 구현
      String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

      Path savePath = Paths.get(saveName);

      try {
        uploadFile.transferTo(savePath); // 실제 이미지 저장
        resultDTOList.add(new UploadFilesDTO(fileName, uuid, folderPath)); //UploadFilesDTO에 추가

      } catch (IOException e) {
        e.printStackTrace();
      }

    }
    return new ResponseEntity<>(resultDTOList, HttpStatus.OK);

  }

  /*날짜 폴더 생성*/
  private String makeFolder() {

    String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

    String folderPath = str.replace("/", File.separator);

    // make folder --------
    File uploadPathFolder = new File(uploadPath, folderPath);

    if(!uploadPathFolder.exists()) {
      boolean mkdirs = uploadPathFolder.mkdirs();
      log.info("-------------------makeFolder------------------");
      log.info("uploadPathFolder.exists(): "+uploadPathFolder.exists());
      log.info("mkdirs: "+mkdirs);
    }

    return folderPath;

  }


  @GetMapping("/display")
  public ResponseEntity<byte[]> getFile(String fileName) { //인코딩된 파일 이름을 byte[]로 받음

    try {
      // 1. 브라우저로부터 받은 파일명(저장경로를 제외한 '폴더경로_UUID_파일명')
      String srcFileName = URLDecoder.decode(fileName, "UTF-8");
      log.info("fileName: " + srcFileName);

      // 2. 저장경로_폴더경로_UUID_파일명
      File file = new File(uploadPath + File.separator + srcFileName);
      log.info("file: " + file);

      // 3. Header에 Content-Type값 지정
      HttpHeaders header = new HttpHeaders();
      header.add("Content-Type", Files.probeContentType(file.toPath()));  // 파일확장자에 따른 MIME타입

      // 4. byte[] 타입으로 반환
      return new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
    } catch (Exception e) {
      throw new RuntimeException("이미지를 불러오는데 실패하였습니다!");
    }
  }



}
