package com.yao.messaging.response;

import com.yao.messaging.enums.Status;
import lombok.Value;

@Value //immutable object
public class Response {
    String message;
    int code;

    public Response(Status status) {
        this.message = status.getMessage();
        this.code = status.getCode();
    }
}
