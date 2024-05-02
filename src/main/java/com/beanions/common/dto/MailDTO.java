package com.beanions.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MailDTO {

    private String address;
    private String title;
    private String message;

}
