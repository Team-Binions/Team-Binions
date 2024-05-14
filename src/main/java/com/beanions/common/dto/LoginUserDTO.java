package com.beanions.common.dto;

import com.beanions.common.MemberRole;
import lombok.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoginUserDTO {

    private Integer memberCode;
    private String memberId;
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String gender;
    private String marriedStatus;
    private MemberRole memberRole;
    private String weddingFile;
    private String weddingVerified;
    private Date signupDate;

    public List<String> getRole(){
        if(!this.memberRole.getRole().isEmpty()) {
            System.out.println("5");
            return Arrays.asList(this.memberRole.getRole().split(","));
        }
        return null;
    }
}
