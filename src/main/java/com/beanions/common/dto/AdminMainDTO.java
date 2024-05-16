package com.beanions.common.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class AdminMainDTO {
    private int memberAll;
    private int memberDay;
    private int postAll;
    private int postDay;
    private int reviewAll;
    private int reviewDay;
    private int yesinAll;
    private int yesinDay;
    private int yerangAll;
    private int yerangDay;
}
