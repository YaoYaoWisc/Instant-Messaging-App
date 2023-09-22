package com.yao.messaging.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserValidationCode {

    private int id;
    private int userId; // user.userId = userValidationCode.userId
    private String validationCode;
}
