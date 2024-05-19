package com.beanions.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Data
@AllArgsConstructor
public class UploadFilesDTO {
  private String fileName;
  private String uuid;
  private String folderPath;

  public String getImageURL() {
    return URLEncoder.encode(folderPath+"/"+uuid+"_"+fileName, StandardCharsets.UTF_8);
  }

}