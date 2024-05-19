package com.beanions.board.common.dto;

import com.beanions.auth.model.AuthDetails;
import lombok.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CommentAndAuthDetailsDto {

    private List<CommentAndMemberDTO> commentAndMemberDTOList;
    private AuthDetails authDetails;
}
