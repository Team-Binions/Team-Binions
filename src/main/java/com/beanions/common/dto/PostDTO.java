package com.beanions.common.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostDTO {

    private int postCode;
    private int memberCode;
    private String postTitle;
    private String postContext;
    private Date postDate;
    private String mainCategory;
    private String subCategory;
    private String verifiedStatus;



}
