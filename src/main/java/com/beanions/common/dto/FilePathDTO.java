package com.beanions.common.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.File;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Component
public class FilePathDTO {

    private String directoryPath;

}
