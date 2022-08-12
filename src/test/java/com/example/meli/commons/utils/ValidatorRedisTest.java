package com.example.meli.commons.utils;
import com.example.meli.commons.exception.RedisExceptionCustom;
import com.example.meli.commons.validator.RedisValidImp;
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
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ValidatorRedisTest {
    @Mock
    private AppEnv appEnv;

    @InjectMocks
    private RedisValidImp redisValidImp;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void RedisTestException() {
        ReflectionTestUtils.setField(appEnv, "redisEnabled", 0);
        assertThrows(RedisExceptionCustom.class, () -> redisValidImp.validator());
    }
    @Test
    public void RedisTest() {
        ReflectionTestUtils.setField(appEnv, "redisEnabled", 0);
        Mockito.when(appEnv.getRedisEnabled()).thenReturn(100);
        redisValidImp.validator();
        verify(appEnv,times(1)).getRedisEnabled();
    }

/*    @Test
    public void ValidatorRedisTestFail() {
        ReflectionTestUtils.setField(appEnv, "redisEnabled", -1);
        assertThrows(NullPointerException.class, () -> redisValidImp.validator());
    }*/


}
