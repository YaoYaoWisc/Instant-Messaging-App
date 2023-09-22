package com.yao.messaging.advice;

import com.yao.messaging.enums.Status;
import com.yao.messaging.exception.MessagingServiceException;
import com.yao.messaging.response.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class ExceptionHandlers {

    @ExceptionHandler(MessagingServiceException.class)
    public ResponseEntity<Response> handleMessagingServiceException(MessagingServiceException messagingServiceException) {
        log.warn("Encountered exception: {}", messagingServiceException.getMessage(), messagingServiceException);
        return ResponseEntity
                .status(messagingServiceException.getStatus().getHttpStatus())
                .body(new Response(messagingServiceException.getStatus()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(Exception exception) {
        log.warn("Encountered exception: {}", exception.getMessage(), exception);
        return ResponseEntity
                .status(Status.UNKNOWN_EXCEPTION.getHttpStatus())
                .body(new Response(Status.UNKNOWN_EXCEPTION));

    }

}
