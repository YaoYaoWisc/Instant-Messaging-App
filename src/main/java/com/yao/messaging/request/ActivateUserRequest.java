package com.yao.messaging.request;

import lombok.Data;

@Data
public class ActivateUserRequest {
    private String identification; // username or email
    private String validationCode;
}
