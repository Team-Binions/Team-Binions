package com.beanions.admin.dto;

import com.beanions.common.dto.CommentsDTO;
import com.beanions.common.dto.MembersDTO;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class AdminMemberPostDTO {

    private int board;
    private int comment;

}
