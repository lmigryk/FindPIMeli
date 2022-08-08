package com.example.meli.domain.services;

import com.example.meli.domain.models.NumberPi;

public interface IPiService {
    NumberPi getPiRandom(int randomNumber);
    NumberPi getPiNotRandom(int number);
}
