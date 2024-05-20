package com.beanions.board.common.dto;

import com.beanions.common.dto.PostDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class PostAndFilesDTO {

    private int fileCode;
    private String fileName;
    private int postCode;

    private PostDTO postDTO;

}
