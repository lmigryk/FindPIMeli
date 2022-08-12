package com.example.meli.domain.model;


import com.example.meli.domain.models.BasePi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BasePiTest {

    private BasePi base;

    @BeforeEach
    public void setup() {
        base = new BasePi(8, 4, "3.1412");
    }


    @Test
    public void ValidatorGetTest() {
        assertEquals(8, base.getParam());
        assertEquals(4, base.getRandom());
        assertEquals("3.1412", base.getPiCalc());
    }

    @Test
    public void ValidatorSetter() {
        base.setParam(2);
        base.setRandom(1);
        base.setPiCalc("3.1");
        assertEquals(2, base.getParam());
        assertEquals(1, base.getRandom());
        assertEquals("3.1", base.getPiCalc());
    }
/*    @Test
    public void ValidatorEquals() {
        boolean b=base.equals(new BasePi(8,4,"3.1412"));
        assertEquals(true, b);

    }
    @Test
    public void NotEquals() {
        boolean b=base.equals(new BasePi(8,9,"3.1412"));
        assertEquals(false, b);

    }*/
}
