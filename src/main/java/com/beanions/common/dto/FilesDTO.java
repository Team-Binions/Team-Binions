package com.beanions.common.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FilesDTO {

    private Integer fileCode;
    private String fileName;
    private int postCode;
}
