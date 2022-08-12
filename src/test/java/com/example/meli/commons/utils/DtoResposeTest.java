package com.example.meli.commons.utils;


import com.example.meli.commons.dto.DtoResponseDelete;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)

public class DtoResposeTest {

    private DtoResponseDelete dto;

    @BeforeEach
    public void setup() {
        dto = new DtoResponseDelete("Se creo",7);
    }


    @Test
    public void ValidatorGetTest() {
        assertEquals("Se creo",dto.getMessage());
        assertEquals(7,dto.getNumber());
    }
    @Test
    public void ValidatorSetter() {
        dto.setMessage("Se creoo ehh");
        dto.setNumber(8);
        assertEquals("Se creoo ehh",dto.getMessage());
        assertEquals(8,dto.getNumber());
    }

}
