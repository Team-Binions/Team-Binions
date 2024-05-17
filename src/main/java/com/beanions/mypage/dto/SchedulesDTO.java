package com.beanions.mypage.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SchedulesDTO {

    private Integer scheduleCode;
    private Integer memberCode;
    private String scheduleTitle;
    private Date scheduleDate;
    private String scheduleContext;

}
