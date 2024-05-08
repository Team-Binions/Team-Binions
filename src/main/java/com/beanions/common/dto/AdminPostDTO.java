package com.beanions.common.dto;

import lombok.*;

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
    private Date postDate;
    private String mainCategory;
    private String subCategory;
    private String verifiedStatus;
    private MembersDTO member;
//    private String nickname;
}
