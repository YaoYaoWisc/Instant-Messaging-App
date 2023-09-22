package com.yao.messaging.model;

import java.util.Date;

import com.yao.messaging.enums.Gender;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {

    private Integer id;
    private String username;
    private String email; //phone number 2FA
    private String password;
    private String address;
    private String nickname;
    private Gender gender;
    private Date registerTime;
    private String loginToken;
    private Date lastLoginTime;
    private Boolean isValid;

}
