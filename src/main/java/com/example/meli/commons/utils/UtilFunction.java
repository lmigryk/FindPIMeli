package com.example.meli.commons.utils;

import com.example.meli.commons.validator.RandomValidImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class UtilFunction {

    public UtilFunction() {
    }
    public int calculatedRandom(int max){
        int min = max/2;
        int random = (int) (Math.random() * (max - min)) + min;
        return random;
    }
}
