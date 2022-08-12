package com.example.meli.commons.exception;

public class DeleteException extends ApiExceptionBase{
    public DeleteException(String userMessage, String internalMessage, String userMoreInfo) {
        super(userMessage, internalMessage, userMoreInfo);
    }
}
