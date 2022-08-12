package com.example.meli.commons.validator;

import com.example.meli.commons.exception.ApiExceptionBase;
import org.springframework.stereotype.Service;

@Service
public interface BasicValid {
    void validator(Integer random) throws ApiExceptionBase;
}
