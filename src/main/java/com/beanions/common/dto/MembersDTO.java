package com.beanions.common.dto;

import com.beanions.common.MemberRole;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MembersDTO {

    private Integer memberCode;
    private String memberId;
    private String memberPw;
    private String nickname;
    private String email;
    private String phone;
    private String gender;
    private String marriedStatus;
//    private MemberRole memberRole;
    private String memberRole;
    private String weddingFile;
    private String weddingVerified;
    private Date signupDate;

}
