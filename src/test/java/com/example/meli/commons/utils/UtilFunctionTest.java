package com.example.meli.commons.utils;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(SpringExtension.class)

public class UtilFunctionTest {

    private final UtilFunction util = new UtilFunction();


    @Test
    public void calculeRandomTest(){
        int into = 20;
        int result = util.calculatedRandom(into);
        assertTrue("Error, random is too high", result >= into/2);
        assertTrue("Error, random is too low",  result  <= into);
    }

}
