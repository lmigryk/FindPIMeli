package com.example.meli.domain.services;

import com.example.meli.domain.models.BasePi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.CacheManager;


import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PiServiceTest {
    @Mock
    private CacheManager cacheManager;

    @InjectMocks
    private PiServiceImp service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void getPiRandomTest(){
        int request =  4;
        BasePi response = new BasePi(request,"3.1415");
        BasePi result = service.getPiRandom(request);
        assertEquals(response.getPiCalc(), result.getPiCalc());
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



