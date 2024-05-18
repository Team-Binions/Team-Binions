package com.beanions.board.common.dto;
import com.beanions.common.dto.CommentsDTO;
import com.beanions.common.dto.MembersDTO;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class PostAndCommentDTO {

    private Integer postCode;
    private Integer memberCode;
    private String postTitle;
    private String postContext;
    private Date postDate;
    private String mainCategory;
    private String subCategory;
    private String verifiedStatus;
    private String reviewStatus;
    private Integer viewCount;
    private MembersDTO member;
    private List<CommentAndMemberDTO> comment;

}
