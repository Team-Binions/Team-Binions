package com.beanions.common.uploadfiles;

import com.beanions.common.dto.FilesDTO;
import com.beanions.common.dto.PostDTO;
import com.beanions.mypage.dao.UploadMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UploadService {
  private final UploadMapper uploadMapper;

  public UploadService(UploadMapper uploadMapper) {this.uploadMapper = uploadMapper;}

  public List<FilesDTO> registWriting() {return uploadMapper.registWriting();}

  public List<FilesDTO> selectAllFiles() {return uploadMapper.selectAllFiles();}

  public int registPost(PostDTO post) {
    return uploadMapper.registPost(post);
  }

  public PostDTO selectPost(int membercode) {
    return uploadMapper.selectPost(membercode);
  }

  public void insertFile(FilesDTO fileInfo) {
    uploadMapper.insertFile(fileInfo);
  }

  @Scheduled(fixedRate = 300000) // ms 기준 / 매번 약 5분마다 실행
  public void deleteUploadFile() {
    Path directory = Path.of("src/main/resources/assets/images/upload/user/uploadTemp");

    if (directory != null && Files.isDirectory(directory)) {
      try {
        LocalDateTime now = LocalDateTime.now();
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-EE HH:mm:ss"));

        Files.walk(directory)
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .forEach(File::delete);
        System.out.println(formatedNow + " --- 글쓰기 업로드 파일이 삭제되었습니다...");
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error occurred while deleting files.");
      }
    } else {
      System.out.println("Directory not found.");
    }
  }

//    public int modifyPost(PostDTO post) {
//    return uploadMapper.modifyPost(post);
//    }
//
//  public void modiftyFile(FilesDTO fileInfo) {
//    uploadMapper.modifyFile(fileInfo);
//  }

  //public List<FilesDTO> registerFileSelected() {return uploadMapper.registerFileSelected();}
}
