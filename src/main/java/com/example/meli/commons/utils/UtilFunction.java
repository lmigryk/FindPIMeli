package com.example.meli.commons.utils;

public class UtilFunction {
        public static int calculatedRandom(int max){
            int min = max/2;
            int random = (int) (Math.random() * (max - min)) + min;
            return random;
        }
}
