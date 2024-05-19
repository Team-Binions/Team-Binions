package com.beanions.common.dto;

import com.beanions.auth.dto.MemberRole;
import lombok.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoginUserDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer memberCode;
    private String memberId;
    private String memberPw;
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
            return Arrays.asList(this.memberRole.getRole().split(","));
        }
        return null;
    }
}
