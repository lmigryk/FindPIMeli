package com.example.meli.domain.services;

import com.example.meli.commons.dto.DtoResponseDelete;
import com.example.meli.domain.models.BasePi;

public interface PiService {
    BasePi getPiRandom(int randomNumber);
    BasePi getPiNotRandom( int number);
    DtoResponseDelete deletePi(int number) ;
}
