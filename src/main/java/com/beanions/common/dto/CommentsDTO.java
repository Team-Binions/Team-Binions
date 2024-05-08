package com.beanions.common.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentsDTO {

    private int commentCode;
    private int postCode;
    private int memberCode;
    private String commentContext;
    private Date commentDate;
}
