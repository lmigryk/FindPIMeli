package com.example.meli.commons.exception;

import lombok.Getter;
import lombok.Setter;

public class ApiExceptionBase extends RuntimeException{
    @Getter
    @Setter
    private final String userMessage;
    @Getter
    @Setter
    private final String internalMessage;
    @Getter
    @Setter
    private final String userMoreInfo;

    public ApiExceptionBase(String userMessage, String internalMessage, String userMoreInfo) {
        this.userMessage = userMessage;
        this.internalMessage = internalMessage;
        this.userMoreInfo = userMoreInfo;
    }
}
