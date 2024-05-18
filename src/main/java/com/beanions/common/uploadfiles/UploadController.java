package com.beanions.common.uploadfiles;

import com.beanions.common.dto.FilesDTO;
import com.beanions.common.dto.UploadFilesDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
