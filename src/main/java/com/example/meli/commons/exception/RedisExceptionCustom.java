package com.example.meli.commons.exception;

public class RedisExceptionCustom extends ApiExceptionBase{
    public RedisExceptionCustom(String userMessage, String internalMessage, String userMoreInfo) {
        super(userMessage, internalMessage, userMoreInfo);
    }
}
