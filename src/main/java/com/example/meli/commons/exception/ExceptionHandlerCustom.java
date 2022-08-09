package com.example.meli.commons.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class ExceptionHandlerCustom extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value={DeleteException.class})
    public ResponseEntity<Object> handlerApiException(DeleteException e){
        DtoResponseException ex = new DtoResponseException(
                e.getUserMessage(),
                e.getInternalMessage(),
                e.getUserMoreInfo());

        return new ResponseEntity<>(ex, HttpStatus.CONFLICT);
    }
}
