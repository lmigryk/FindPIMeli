package com.example.meli.domain.services;

import com.example.meli.commons.exception.DeleteException;
import com.example.meli.domain.models.BasePi;

public interface IPiService {
    BasePi getPiRandom(int randomNumber, int param);
    BasePi getPiNotRandom(int number);
    void deletePi(int number) ;
}
