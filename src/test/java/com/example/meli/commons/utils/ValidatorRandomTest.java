package com.example.meli.commons.utils;
import com.example.meli.commons.exception.RandomException;
import com.example.meli.commons.validator.RandomValidImp;
import com.example.meli.config.AppEnv;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ValidatorRandomTest {
    @Mock
    private AppEnv appEnv;

    @InjectMocks
    private RandomValidImp randomValidImp;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void ValidatorRandomTestException() {
        ReflectionTestUtils.setField(appEnv, "maxRandomPrecision", 50);
        assertThrows(RandomException.class, () -> randomValidImp.validator(70));
    }

    @Test
    public void ValidatorRandomTestOk() {
        ReflectionTestUtils.setField(appEnv, "maxRandomPrecision", 40);
        Mockito.when(appEnv.getMaxRandomPrecision()).thenReturn(40);
        randomValidImp.validator(3);
        verify(appEnv,times(1)).getMaxRandomPrecision();

        //assertEquals(1,1);
    }



}
