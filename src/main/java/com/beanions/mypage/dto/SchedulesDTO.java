package com.beanions.mypage.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SchedulesDTO {

    private int scheduleCode;
    private int memberCode;
    private String scheduleTitle;
    private Date scheduleDate;
    private String scheduleContext;

}
