package com.example.meli.commons.exception;

import lombok.Getter;
import lombok.Setter;

import javax.validation.ConstraintDeclarationException;

public class RandomException extends ApiExceptionBase {
    @Getter
    @Setter
    private final Integer randomGenerate;
    public RandomException(String userMessage, String internalMessage, String userMoreInfo, Integer randomGenerate) {
        super(userMessage, internalMessage, userMoreInfo);
        this.randomGenerate = randomGenerate;
    }


}
