package com.example.meli.commons.utils;

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
