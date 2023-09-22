package com.yao.messaging.enums;

import org.springframework.http.HttpStatus;

public enum Status {

    OK(1000, "Successful", HttpStatus.OK),
    PASSWORDS_NOT_MATCHED(1001, "Passwords not matched", HttpStatus.BAD_REQUEST),
    TOO_SHORT_PASSWORD(1002, "Too short passwords", HttpStatus.BAD_REQUEST),
    USERNAME_ALREADY_EXISTS(1003, "Username already exists", HttpStatus.BAD_REQUEST),
    UNKNOWN_EXCEPTION(9999, "Unknown exception", HttpStatus.INTERNAL_SERVER_ERROR);

    private int code;
    private String message; //i18n internationalization
    private HttpStatus httpStatus;

    Status(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}