package com.example.meli.commons.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SerialPiTest {

    private final String RESULT = "3.1333";
    private final SerialPi serial = new SerialPi(4);

    @Test
    public void calculeFirstIteration(){
        BigDecimal response = new BigDecimal(RESULT);
        BigDecimal result = serial.calculateIteration(0);
        assertEquals(response, result);
    }

}
