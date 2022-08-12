package com.example.meli.domain.services;

import com.example.meli.commons.exception.RandomException;
import com.example.meli.commons.utils.UtilFunction;
import com.example.meli.commons.validator.RandomValidImp;
import com.example.meli.domain.models.BasePi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.CacheManager;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PiServiceTest {
    @Mock
    private CacheManager cacheManager;

    @Mock
    private UtilFunction util;

    @Mock
    private RandomValidImp randomValidImp;

    @InjectMocks
    private PiServiceImp service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void getPiRandomTest(){
        BasePi numero= new BasePi(4,"3.1415");
        Mockito.when(util.calculatedRandom(4)).thenReturn(4);
        BasePi result = service.getPiRandom(4);
        assertEquals(numero.getPiCalc(), result.getPiCalc());
    }
    @Test
    public void getPiRandomTestException() throws Exception {
       Mockito.when(util.calculatedRandom(80)).thenReturn(20);
       doThrow(new RandomException("","","",24)).when(randomValidImp).validator(20);
        assertThrows(RandomException.class, () -> {
            service.getPiRandom(80);
        });
    }

    @Test
    public void getPiNoRandomTest(){
        int request =  4;
        BasePi response = new BasePi(request,"3.1415");
        BasePi result = service.getPiNotRandom(request);
        assertEquals(response.getPiCalc(), result.getPiCalc());
    }

/*  @Test
    public void deleteTest() {int request = 4;
      CacheManager cacheManager = mock(CacheManager.class);
      Cache cache = mock(Cache.class);

      Mockito.when(cacheManager.getCache("numer_pi")).thenReturn(cache);
      Mockito.when(cache.get(request)).thenReturn(null);

      assertThrows(NullPointerException.class, () -> {
            DtoResponseDelete result = service.deletePi(request);
        });
    }*/
}



