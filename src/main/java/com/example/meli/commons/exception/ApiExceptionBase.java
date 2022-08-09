package com.example.meli.commons.exception;

import lombok.Getter;
import lombok.Setter;

import javax.validation.ConstraintDeclarationException;

public class ApiExceptionBase extends ConstraintDeclarationException {
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
        super();
        this.userMessage = userMessage;
        this.internalMessage = internalMessage;
        this.userMoreInfo = userMoreInfo;

    }
}
