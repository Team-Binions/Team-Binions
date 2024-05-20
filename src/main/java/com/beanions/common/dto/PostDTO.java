package com.beanions.common.dto;

import lombok.*;

import java.sql.Timestamp;
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
    private Timestamp postDate;
    private String mainCategory;
    private String subCategory;
    private String verifiedStatus;
    private String reviewStatus;
    private int viewCount;
}
