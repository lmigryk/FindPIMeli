package com.example.meli.commons.exception;

public class RandomException extends ApiExceptionBase{
    private final String randomGenerate;
    public RandomException(String userMessage, String internalMessage, String userMoreInfo, String randomGenerate) {
        super(userMessage, internalMessage, userMoreInfo);
        this.randomGenerate = randomGenerate;
    }


}
