package com.yao.messaging.request;

import com.yao.messaging.enums.Gender;
import lombok.Data;

@Data
public class RegisterUserRequest {
    private String username;
    private String email;
    private String password;
    private String repeatPassword;
    private String address;
    private String nickname;
    private Gender gender;
}
