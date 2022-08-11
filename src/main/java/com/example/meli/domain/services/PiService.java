package com.example.meli.domain.services;

import com.example.meli.commons.exception.ApiExceptionBase;
import com.example.meli.commons.exception.DeleteException;
import com.example.meli.commons.exception.RandomException;
import com.example.meli.commons.validator.RandomConstraint;
import com.example.meli.domain.models.BasePi;

import javax.validation.constraints.Min;

public interface IPiService {
    BasePi getPiRandom(int randomNumber);
    BasePi getPiNotRandom( int number);
    void deletePi( int number) ;
}
