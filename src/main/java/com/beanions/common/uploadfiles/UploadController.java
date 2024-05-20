package com.beanions.common.uploadfiles;

import com.beanions.common.dto.FilesDTO;
import com.beanions.common.dto.PostDTO;
import com.beanions.common.dto.UploadFilesDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

  @PostMapping(value = "/user/registPost")
  @ResponseBody
  public String registPost(@RequestBody PostDTO post) throws JsonProcessingException {

    int result = uploadService.registPost(post);

    if(result > 0) {
      PostDTO postDTO = uploadService.selectPost(post.getMemberCode());
      return new ObjectMapper().writeValueAsString(postDTO);
    }
    return new ObjectMapper().writeValueAsString(null);
  }

  /*파일 업로드, 업로드 결과 반환*/
  @PostMapping("/user/uploadAjax")
  public String uploadFile(@RequestParam(value="file",required = false) MultipartFile file) throws JsonProcessingException {

//    List<UploadFilesDTO> resultDTOList = new ArrayList<>();
//      List<FilesDTO> uploadFileList = new ArrayList<>();

      // 이미지 파일만 업로드
//      if (!Objects.requireNonNull(uploadFile.getContentType()).startsWith("image")) {
//        log.warn("this file is not image type");
//        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//      }


//      // 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로 => 바뀐 듯 ..
//      String orginalName = uploadFile.getOriginalFilename();
////      assert orginalName != null;
//      String fileName = orginalName.substring(orginalName.lastIndexOf("\\") + 1);
//
//      log.info("fileName: " + fileName);
//
//      // 날짜 폴더 생성
//      //String folderPath = makeFolder();
//
//      // UUID
//      String uuid = UUID.randomUUID().toString();
//
//      // 저장할 파일 이름 중간에 "_"를 이용해서 구현
//      String saveName = uploadPath + File.separator + uuid + "_" + fileName;
//
//      Path savePath = Paths.get(saveName);
//
//      try {
//        uploadFile.transferTo(savePath); // 실제 이미지 저장
////        resultDTOList.add(new UploadFilesDTO(fileName, uuid, uploadPath)); //UploadFilesDTO에 추가
//        uploadFileList.add(new FilesDTO(5, saveName, 5));
//        System.out.println("uploadFileList from AJAX = " + uploadFileList);
//        uploadService.registWriting(); // Files내용 DB 저장
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//
//    return new ResponseEntity<>(resultDTOList, HttpStatus.OK);

    String root = "src/main/resources/assets/images/upload";
    String filePath = root + "/user/uploadTemp";

    File dir = new File(filePath);

    if( !dir.exists() ) {
      dir.mkdirs();
    }

    /* 파일명 변경하기 */
    String originFileName = file.getOriginalFilename();
    System.out.println("originFileName : " + originFileName);
    String ext = originFileName.substring(originFileName.lastIndexOf("."));
//        System.out.println("ext : " + ext);

    String savedName = UUID.randomUUID() + ext;
    System.out.println("savedName : " + savedName);


    /* 파일 저장 */
    try {
      Path path= Paths.get(filePath + "/" + savedName).toAbsolutePath();
      file.transferTo(path.toFile());
//            file.transferTo(new File(filePath + "/" + savedName));

    } catch (IOException e) {
      System.out.println("error : " + e);
    }

    return new ObjectMapper().writeValueAsString(savedName);

  }

  // 비동기로 이미지 파일 보여주는 경로
  @GetMapping("/user/display")
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

  @PostMapping("/user/registerFiles")
  public Object multiFileUpload(@RequestParam List<FilesDTO> uploadFiles, Model model) {

    if(uploadFiles == null) {
      return ResponseEntity.ok().build();
    }

    String fileName;
    String root = "src/main/resources/assets/images/upload";
    String filePath = root + "/user/uploadTemp/";
    String copyFolderPath = root + "/user/upload/";
    File dir = new File(filePath);

    if (!dir.exists()) {
      dir.mkdir();
    }

    try {
      for (FilesDTO fileInfo : uploadFiles) {

        fileName = fileInfo.getFileName();
        filePath = filePath + fileName;
        File file = new File(filePath);

        Path sourcePath = Paths.get(filePath);
        System.out.println("임시파일 저장 폴더 : " + sourcePath);
        if (!Files.exists(sourcePath)) {
          // 파일이 존재하지 않는 경우 404 Not Found 응답 반환
          return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        System.out.println("multiFile = " + fileInfo);

        Path destinationPath = Paths.get(copyFolderPath + file.getName());
        Files.move(sourcePath, destinationPath);
        System.out.println("복사된 폴더 경로 : " + destinationPath);

        uploadService.insertFile(fileInfo);
//        uploadService.registWriting(); // Files내용 DB 저장
      }
    } catch (IOException e) {
      //throw new RuntimeException(e);
      /* 파일 저장 중간에 실패 시 이전에 저장된 파일 삭제*/
      for (FilesDTO file : uploadFiles) {
        new File(filePath + "/" + file.getFileName()).delete();
      }
      System.out.println("다중 파일 업로드 실패🤬");
    }

    return "redirect:/";
  }
}