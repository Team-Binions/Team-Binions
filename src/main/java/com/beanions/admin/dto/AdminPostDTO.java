package com.beanions.admin.dto;

import lombok.*;
import com.beanions.common.dto.MembersDTO;
import java.util.Date;

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
    private Date postDate;
    private String mainCategory;
    private String subCategory;
    private String verifiedStatus;
    private String reviewStatus;
    private int viewCount;
    private MembersDTO member;
//    private String nickname;
}
