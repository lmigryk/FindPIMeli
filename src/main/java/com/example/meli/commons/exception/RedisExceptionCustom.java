package com.example.meli.commons.exception;

public class RedisException extends ApiExceptionBase{
    public RedisException(String userMessage, String internalMessage, String userMoreInfo) {
        super(userMessage, internalMessage, userMoreInfo);
    }
}
