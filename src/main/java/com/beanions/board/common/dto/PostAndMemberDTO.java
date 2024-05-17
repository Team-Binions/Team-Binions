package com.beanions.board.common.dto;
import com.beanions.common.dto.MembersDTO;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class PostAndMemberDTO {

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

}
