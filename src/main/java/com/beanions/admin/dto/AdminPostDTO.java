package com.beanions.admin.dto;

import com.beanions.common.dto.FilesDTO;
import lombok.*;
import com.beanions.common.dto.MembersDTO;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class AdminPostDTO {

    private int postCode;
    private int memberCode;
    private String postTitle;
    private String postContext;
    private Timestamp postDate;
    private String mainCategory;
    private String subCategory;
    private String verifiedStatus;
    private String reviewStatus;
    private int viewCount;
    private MembersDTO member;
    private List<FilesDTO> file;
//    private String nickname;
}
