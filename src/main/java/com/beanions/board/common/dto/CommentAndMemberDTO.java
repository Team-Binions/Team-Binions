package com.beanions.board.common.dto;

import com.beanions.common.dto.MembersDTO;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class CommentAndMemberDTO {

    private Integer commentCode;
    private Integer postCode;
    private Integer memberCode;
    private String commentContext;
    private Date commentDate;
    private MembersDTO member;

}
