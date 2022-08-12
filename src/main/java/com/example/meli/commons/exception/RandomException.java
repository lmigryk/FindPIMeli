package com.example.meli.commons.exception;

import lombok.Getter;
import lombok.Setter;


public class RandomException extends ApiExceptionBase {
    @Getter
    @Setter
    private  Integer randomGenerate;
    public RandomException(String userMessage, String internalMessage, String userMoreInfo, Integer randomGenerate) {
        super(userMessage, internalMessage, userMoreInfo);
        this.randomGenerate = randomGenerate;
    }


}
