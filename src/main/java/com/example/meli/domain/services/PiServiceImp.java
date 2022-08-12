package com.example.meli.domain.services;

import com.example.meli.commons.dto.DtoResponseDelete;
import com.example.meli.commons.exception.DeleteException;
import com.example.meli.commons.constants.ExceptionEnum;
import com.example.meli.commons.utils.SerialPi;
import com.example.meli.commons.utils.UtilFunction;
import com.example.meli.commons.validator.RandomValidImp;
import com.example.meli.domain.models.BasePi;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Validated
@Service
public class PiServiceImp implements PiService {
    private  UtilFunction util;
    private  RandomValidImp randomValidImp;
    private  CacheManager cacheManager ;
    private final DtoResponseDelete response = new DtoResponseDelete();

    public PiServiceImp(CacheManager cacheManager, UtilFunction utilFunction, RandomValidImp randomValidImp) {

        this.cacheManager = cacheManager;
        this.randomValidImp = randomValidImp;
        this.util = utilFunction;
    }
    @Override
    @Cacheable(cacheNames = "number_pi", key = "#inputUser" ,unless = "#result == null")
    public BasePi getPiRandom(int inputUser) {
        System.out.println("No estaba creado " + inputUser + " se creará ");
        int random = util.calculatedRandom(inputUser);
        randomValidImp.validator(random);
        return calculatedPi(random);
    }
    @Override
    @Cacheable(cacheNames = "number_pi", key = "#numberUSer" ,unless = "#result == null")
    public BasePi getPiNotRandom(int numberUSer) {
        System.out.println("No estaba creado " + numberUSer + " se creará ");
        return calculatedPi(numberUSer);
    }

    @Override
    @CacheEvict(cacheNames = "number_pi", key = "#numberUSer")
    public DtoResponseDelete deletePi(int numberUSer)  {
        Cache cache = cacheManager.getCache("number_pi");
        if (cache.get(numberUSer) != null) {
            response.setMessage("Number pi deleted correctly");
            response.setNumber(numberUSer);
            return response;
        }
        else{
            throw new DeleteException(ExceptionEnum.DELETE_INFO.getData(),
                    ExceptionEnum.DELETE_MESSAGE.getData(),
                    ExceptionEnum.DELETE_URL.getData());
        }

    }
    public BasePi calculatedPi( int decimales){
        SerialPi serie = new SerialPi(decimales);
        int incremento = 0;
        BigDecimal sumatoria =  new BigDecimal(0), anteriorSumatoria;
        boolean variable = true;
        while (variable){
            anteriorSumatoria = sumatoria;
            sumatoria = sumatoria.add(serie.calculateIteration(incremento));
            incremento +=1;
            variable = !sumatoria.setScale(decimales, RoundingMode.FLOOR).equals(anteriorSumatoria.setScale(decimales, RoundingMode.FLOOR));
        }

        return new BasePi(decimales,sumatoria.setScale(decimales, RoundingMode.FLOOR).toString());

    }

}
