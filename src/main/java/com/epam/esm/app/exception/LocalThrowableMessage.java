package com.epam.esm.app.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data @AllArgsConstructor
public class LocalThrowableMessage {

    private HttpStatus httpStatus;
    private String message;
}
