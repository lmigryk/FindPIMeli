package com.example.meli.commons.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerCustom extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RandomException.class)
    public ResponseEntity<?> handleRandomException(RandomException e) {
        DtoResponseException ex = new DtoResponseException(
                e.getUserMessage(),
                e.getInternalMessage(),
                e.getUserMoreInfo(),
                e.getRandomGenerate());
        return new ResponseEntity<>(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RedisException.class)
    public ResponseEntity<?> handleRedisException(RedisException e) {
        DtoResponseException ex = new DtoResponseException(
                e.getUserMessage(),
                e.getInternalMessage(),
                e.getUserMoreInfo());


        return new ResponseEntity<>(ex, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value={DeleteException.class})
    public ResponseEntity<Object> handlerApiException(DeleteException e){
        DtoResponseException ex = new DtoResponseException(
                e.getUserMessage(),
                e.getInternalMessage(),
                e.getUserMoreInfo());

        return new ResponseEntity<>(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationException(ConstraintViolationException e) {
        List<String> errors = new ArrayList<>();

        e.getConstraintViolations().forEach(cv -> errors.add(cv.getMessage()));
        Map<String, List<String>> result = new HashMap<>();
        result.put("errors", errors);

        DtoResponseException ex = new DtoResponseException(
                errors.toString(),
                "NUMER_MINOR_1",
                "https://httpstatuses.com/400");


        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }




}
