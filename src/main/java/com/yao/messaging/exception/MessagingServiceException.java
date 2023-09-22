package com.yao.messaging.exception;

import com.yao.messaging.enums.Status;

public class MessagingServiceException extends Exception {
    private Status status;

    public MessagingServiceException(Status status) {
        super(status.getMessage());
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
